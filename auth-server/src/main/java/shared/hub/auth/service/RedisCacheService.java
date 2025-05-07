package shared.hub.auth.service;

public interface RedisCacheService {

    void setString(String key, String value);

    String getString(String key);

    void setObject(String key, Object value);

    <T> T getObject(String key, Class<T> targetClass);

    void delete(String key);
}
