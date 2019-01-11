package org.rapp.comm.redis.utils;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis工具
 * @author 张芳
 *
 */
@Component
public class JedisHelper {
	
    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    /**
     * 向Redis写入内容
     * @param key
     * @param value
     * @param seconds
     */
    public void setString(final String key, final String value, final long seconds) {
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(redisTemplate.getStringSerializer().serialize(key), seconds,
                        redisTemplate.getStringSerializer().serialize(value));
                return null;
            }
        });
    }

    /**
     * 从Redis读取内容
     * @param key
     * @return
     */
    public String getString(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
                if (connection.exists(byte_key)) {
                    byte[] byte_value = connection.get(byte_key);
                    return redisTemplate.getStringSerializer().deserialize(byte_value);
                }
                return null;
            }
        });
    }
    
    /**
     * 向Redis写入对象
     * @param key
     * @param value
     * @param seconds
     */
    public void setObject(final String key, final Object value, final long seconds){
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(redisTemplate.getStringSerializer().serialize(key), seconds,
                        SerializeUtil.serialize(value));
                return null;
            }
        });
    }
    
    /**
     * 从Redis读取一个对象
     * @param key
     * @return
     */
    public Object getObject(final String key) {
        return redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
                if (connection.exists(byte_key)) {
                    byte[] byte_value = connection.get(byte_key);
                    return SerializeUtil.unserialize(byte_value);
                }
                return null;
            }
        });
    }
    
    /**
     * 从Redis读取一个对象
     * @param key
     * @param clazz
     * @return
     */
    public <T> T getObject(final String key,Class<T> clazz) {
        return redisTemplate.execute(new RedisCallback<T>() {
            @Override
            @SuppressWarnings("unchecked")
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
                if (connection.exists(byte_key)) {
                    byte[] byte_value = connection.get(byte_key);
                    return (T)SerializeUtil.unserialize(byte_value);
                }
                
                return null;
            }
        });
    }
    
    /**
     * 从Redis中删除一个对象
     * @param keys
     * @return
     */
    public Long delete(final String... keys){
        return redisTemplate.execute(new RedisCallback<Long>(){
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                if(keys != null && keys.length > 0){
                    for(String key : keys){
                        byte[] keyByte = redisTemplate.getStringSerializer().serialize(key);
                        if(connection.exists(keyByte)){
                            return connection.del(keyByte);
                        }
                    }
                }
                return Long.valueOf(0);
            }
        });
    }
    
    /**
     * 设置Redis对象过期时间
     * @param key
     * @param seconds
     * @return
     */
    public Boolean expire(final String key,final long seconds){
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] byteKey = redisTemplate.getStringSerializer().serialize(key);
                if(connection.exists(byteKey)){
                    return connection.expire(byteKey, seconds);
                }
                return Boolean.valueOf(false);
            }
        });
    }
}
