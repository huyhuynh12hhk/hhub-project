package shared.hub.auth.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shared.hub.auth.exception.AppException;
import shared.hub.auth.exception.NotifyCode;
import shared.hub.auth.model.cache.UserCache;
import shared.hub.auth.repository.AppUserRepository;
import shared.hub.auth.service.redission.RedisDistributeUtil;
import shared.hub.auth.service.redission.RedisDistributedLocker;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCacheService {
    private final RedisCacheService redisCacheService;
    private final RedisDistributeUtil redisDistributeUtil;
    private final AppUserRepository userRepository;

    private static final Cache<String, UserCache> userLocalCache = CacheBuilder.newBuilder()
            .initialCapacity(10)
            .concurrencyLevel(12)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();

    public UserCache getUser(String userId, Long version) {
        UserCache userCache = getUserLocalCache(userId);
        if (userCache != null) {
            // If version time <= cache issued time
            if (version == null || version <= userCache.getVersion()) {
                return userCache;
            }
        }
        // Others case
        return getDistributedCacheUser(userId);
    }

    private UserCache getUserLocalCache(String userId) {
        return userLocalCache.getIfPresent(userId);
    }

    private UserCache getDistributedCacheUser(String userId) {
        // Get from cache
        var userCache = redisCacheService.getObject(getItemKeyOf(userId), UserCache.class);
        // Not found -> get from database and set one
        if (userCache == null) {
            log.info("User {} not found, get from database", userId);
            userCache = getDatabaseUser(userId);
        }
        // Put to local cache
        userLocalCache.put(userId, userCache);

        return userCache;
    }

    private UserCache getDatabaseUser(String userId) {
        RedisDistributedLocker locker = redisDistributeUtil.getDistributedLock(getLockKeyOf(userId));
        try {
            // Start lock
            boolean isLock = locker.tryLock(1, 5, TimeUnit.SECONDS);
            if (!isLock) {
                return null;
            }

            // Get cache
            var key = getItemKeyOf(userId);
            var userCache = redisCacheService.getObject(key, UserCache.class);
            if (userCache != null) {
                return userCache;
            }

            // If not cached
            var user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Exist"));
            userCache = new UserCache().cloneFrom(user).withVersion(System.currentTimeMillis());
            redisCacheService.setObject(key, userCache);
            return userCache;
        } catch (Exception e) {
            log.error("Cache User got exception: {}", e);
            throw new AppException(NotifyCode.NOT_FOUND);
        } finally {
            locker.unlock();
        }
    }

    private String getLockKeyOf(String id) {
        return "IDENTITY:USER_LOCK" + id;
    }

    private String getItemKeyOf(String id) {
        return "IDENTITY:USER:" + id;
    }
}