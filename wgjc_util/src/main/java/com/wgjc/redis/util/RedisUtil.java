package com.wgjc.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: redis工具类
 * @author hc
 * @date 2019年8月1日下午3:57:10
 */
@Component
public class RedisUtil{
	@Autowired
	@Qualifier(value = "redisTemplate")
    RedisTemplate<String, String> template;
	
    public <T> boolean set(String key, T value) {
        return set(key, JSONObject.toJSONString(value),0);
    }

    public boolean set(String key, String value, long validTime) {
        boolean result = template.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = template.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                if(validTime > 0) {
                	connection.expire(serializer.serialize(key), validTime);
                }
                return true;
            }
        });
        return result;
    }

    public <T> T get(String key, Class<T> clazz) {
        return JSONObject.parseObject(get(key), clazz);
    }

    public String get(String key) {
        String result = template.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = template.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    public boolean del(String key) {
        return template.delete(key);
    }
}
