package com.zhxshark.personmessage.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author zhuxin
 * @date 2019/10/12 10:03
 */
@Service
public class RedisCacheUtil<T> {
    private final static String RedisCacheUtilKeyPre = "RedisCacheUtilKeyPre_";
    private final static String RedisCacheUtilLockKeyPre = "RedisCacheUtilLockKeyPre_";
    @Autowired
    private RedisLock redisLock;

//    private T getCacheObject(String key, Long expires, Class deskClass, boolean isList, Cacheable cacheable){
//
//    }

}
