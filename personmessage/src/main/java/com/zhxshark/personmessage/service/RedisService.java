package com.zhxshark.personmessage.service;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuxin
 * @date 2019/10/11 15:01
 */
public interface RedisService {
    /**
     * 是否存在某个key
     * @param key
     * @return
     */
    boolean existsKey(String key);
    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    void renameKey(String oldKey, String newKey);
    /**
     * newKey不存在时才重命名
     *
     * @param oldKey
     * @param newKey
     * @return 修改成功返回true
     */
    boolean renameKeyNotExist(String oldKey, String newKey);
    /**
     * 删除key
     *
     * @param key
     */
    void deleteKey(String key);
    /**
     * 删除多个key
     *
     * @param keys
     */
    void deleteKey(String... keys);

    /**
     * 删除Key的集合
     *
     * @param keys
     */
    void deleteKey(Collection<String> keys);
    /**
     * 设置默认过期时长24小时
     * @param key
     */
    void expireKey(String key);
    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    void expireKey(String key, long time, TimeUnit timeUnit);
    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    void expireKeyAt(String key, Date date);
    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    long getKeyExpire(String key, TimeUnit timeUnit);
    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    void persistKey(String key);
}
