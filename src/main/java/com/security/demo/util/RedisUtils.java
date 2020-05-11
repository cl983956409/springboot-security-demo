package com.security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @date 2020/4/23 - 22:39
 * @history 2020/4/23 - 22:39 chenglonghy  create.
 */
public class RedisUtils {

    @Autowired
    private static RedisTemplate redisTemplate;

    /**
     * todo 实用程序类不应该具有公共构造函数
     * 私有构造器
     * 禁止通过反射构造类实例
     *
     * @throws IllegalAccessException 非法访问异常
     */
    private RedisUtils() throws IllegalAccessException {
        throw new IllegalAccessException("禁止访问RedisUtils私有构造方法");
    }

    /**
     * TODO 重要知识点
     * Redis底层对象
     */
    @Component
    static final class Redis {
        Redis(@Autowired StringRedisTemplate redisTemplate) {
            RedisUtils.redisTemplate = redisTemplate;
        }
    }

    public static boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    public static void set(String key, String value) {
        redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public static void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public static void set(String key, String value, long timeout, TimeUnit timeUtils) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUtils);
    }

    public static String get(String key) {
        return o2s(redisTemplate.opsForValue().get(key));
    }

    public static String hget(String key, String value) {
        return o2s(redisTemplate.opsForHash().get(key, value));
    }

    public static Map<String, String> hgetAll(String key) {
        HashOperations<String, String, String> vo = redisTemplate.opsForHash();
        Map<String, String> map = new HashMap<>();
        for (String haskey : vo.keys(key)) {
            String value = vo.get(key, haskey);
            map.put(haskey, value);
        }
        return map;
    }

    public static void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * object 转 String
     */
    private static String o2s(Object obj) {
        return obj == null ? null : obj.toString();
    }
}
