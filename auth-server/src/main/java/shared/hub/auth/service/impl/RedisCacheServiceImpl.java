package shared.hub.auth.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import shared.hub.auth.service.RedisCacheService;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisCacheServiceImpl implements RedisCacheService {
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setString(String key, String value) {
        if (StringUtils.hasLength(key)) { // null or ''
            return;
        }
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String getString(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key))
                .map(String::valueOf)
                .orElse(null);
    }

    @Override
    public void setObject(String key, Object value) {
        if (!StringUtils.hasLength(key)) { // null or ''
            log.info("RedisCacheService - setObject empty, {}", StringUtils.hasLength(key));
            return;
        }

        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            log.error("RedisCacheService - setObject error:{}", e.getMessage());
        }
    }

    @Override
    public <T> T getObject(String key, Class<T> targetClass) {
        Object result = redisTemplate.opsForValue().get(key);
        // Case null
        if (result == null) {
            return null;
        }

        // Case Map object
        if (result instanceof Map<?,?>) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.convertValue(result, targetClass);
            } catch (IllegalArgumentException e) {
                log.error("RedisCacheService - Error while converting LinkedHashMap to object: {}", e.getMessage());
                return null;
            }
        }

        // Case String object
        if (result instanceof String) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue((String) result, targetClass);
            } catch (JsonProcessingException e) {
                log.error("RedisCacheService - Error while deserializing JSON to object: {}", e.getMessage());
                return null;
            }
        }

        return null;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
