package shared.hub.auth.service.redission;

public interface RedisDistributeUtil {
    RedisDistributedLocker getDistributedLock(String lockKey);
}
