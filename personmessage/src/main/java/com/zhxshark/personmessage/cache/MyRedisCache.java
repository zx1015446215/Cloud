package com.zhxshark.personmessage.cache;

import com.zhxshark.personmessage.model.ZxUser;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhuxin
 * @date 2019/10/10 19:33
 */

@Configuration
public class MyRedisCache implements Cache, ApplicationContextAware {

    private static ApplicationContext applicationContext;
    /**
     * 日志记录工具
     */
    private static final Logger logger = LoggerFactory.getLogger(MyRedisCache.class);

    /**
     * 读写锁
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * id
     */
    private String id;

    RedisTemplate redisTemplate;

//    /**
//     * Jackson-databind对象序列化器（核心）
//     */
//    private static ObjectMapper objectMapper;

//    static {
//        objectMapper = new ObjectMapper();
//        //所有属性均可序列化
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        /**
//         * 序列化类型信息（使用objectMapper.readValue(hashVal, Object.class)也可以实现相应类型的序列化和反序列化
//         * 好处：只定义一个序列化器就可以了（通用））
//         */
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//    }


    public MyRedisCache(String id){
        logger.warn("有参构造方法");
        if(id==null){
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    public MyRedisCache(){
        logger.warn("无参构造方法");
    }

    @Override
    public String getId() {
        logger.warn("getId");
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        logger.warn("putObject");
        redisTemplate = getRedisTemplate();
        redisTemplate.opsForValue().set(String.valueOf(key), value,60, TimeUnit.MINUTES);
    }

    @Override
    public Object getObject(Object key) {
        logger.warn("getObject");
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        return redisTemplate.opsForValue().get(String.valueOf(key));
    }

    @Override
    public Object removeObject(Object key) {
        logger.warn("removeObject");
        redisTemplate = getRedisTemplate();
        return redisTemplate.delete(String.valueOf(key));
    }

    @Override
    public void clear() {
        logger.warn("clear");
        redisTemplate = getRedisTemplate();
        Set keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    @Override
    public int getSize() {
        logger.warn("getSize");
        return 0;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            MyRedisCache.applicationContext =applicationContext;
    }

    public RedisTemplate getRedisTemplate(){
        if(redisTemplate==null){
            redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        }
        return  redisTemplate;
    }
}
