package com.zhxshark.personmessage.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhuxin
 * @date 2019/10/12 10:12
 */
@Configuration
public class RedisLock {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * 重试时间
     */
    private static final int DEFAULT_ACQUIRY_RETRY_MILLIS = 100;
    /**
     * 锁的后缀
     */
    private static final String LOCK_SUFFIX = "_redis_lock";
    /**
     * 锁的key
     */
    private String lockKey;
    /**
     * 锁超时时间，防止线程在入锁以后，防止阻塞后面的线程无法获取锁
     */
    private int expireMsecs = 60 * 1000;
    /**
     * 线程获取锁的等待时间
     */
    private int timeoutMsecs = 10 * 1000;
    /**
     * 是否锁定标志
     */
    private volatile boolean locked = false;

    public RedisLock() {
    }

    /**
     * 构造函数
     * @param lockKey 锁的key
     * @param timeoutMsecs  获取锁的超时时间
     */


    public RedisLock(String lockKey,int timeoutMsecs){
        this.lockKey = lockKey;
        this.timeoutMsecs = timeoutMsecs;
    }

    /**
     * 构造函数
     * @param lockKey  锁的key
     * @param timeoutMsecs  获取锁的超时时间
     * @param expireMsecs 锁的有效时间
     */
    public RedisLock(String lockKey,int timeoutMsecs,int expireMsecs){
        this(lockKey,timeoutMsecs);
        this.expireMsecs = expireMsecs;
    }

    /**
     * 获取锁名
     * @return
     */
    public String getLockKey(){
        return lockKey;
    }

    /**
     * 获取某个key的值
     * @param key
     * @return
     */
    private String get(final String key){
        Object obj = redisTemplate.opsForValue().get(key);
        return String.valueOf(obj);
    }

    /**
     * 设置如果不存在key
     * @param key
     * @param value
     * @return
     */
    private boolean setNX(final String key, final String value){
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    private String getSet(final String key, final String value){
        Object obj = redisTemplate.opsForValue().getAndSet(key, value);
        return String.valueOf(obj);
    }

    /**
     * 加锁
     * @return
     * @throws InterruptedException
     */
    private synchronized boolean lock() throws InterruptedException{
        int timeout = timeoutMsecs;
        while (timeout>0){
            long expires = System.currentTimeMillis()+expireMsecs+1;
            String expiresStr = String.valueOf(expires); //锁的有效时间截止点
            if(this.setNX(lockKey,expiresStr)){  //如果lockKey不存在
                locked = true;
                return  true;
            }
            //如果lockKey存在
            String currentValue = this.get(lockKey);  //获取过期的时间
            if(currentValue != null && Long.parseLong(currentValue) < System.currentTimeMillis()){  //如果锁已经过期
                //设置新锁并返回旧值
                String oldValue = this.getSet(lockKey, expiresStr);
                //比较锁的时间，如果不一致可能就是其他锁已经修改了值
                if (oldValue != null && oldValue.equals(currentValue)) {
                    locked = true;
                    return true;
                }
            }
            timeout -=DEFAULT_ACQUIRY_RETRY_MILLIS;
            //延时
            Thread.sleep(DEFAULT_ACQUIRY_RETRY_MILLIS);
        }
        return false;
    }

    /**
     * 释放获取到的锁
     */
    public synchronized  void unlock() {
        if (locked) {
            redisTemplate.delete(lockKey);
        }
    }
}
