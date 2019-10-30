package com.zhxshark.fileexplorer.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author zhuxin
 * @date 2019/10/30 15:09
 */

/**
 * 此操作是在Executor做的操作
 * 针对mybatis的一级缓存做的优化，强行指定flushCacheRequired为true，每次都会刷新缓存
 */

@Component
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})})
public class PageLocalCacheDisableInterceptor implements Interceptor {

    private static final String DEFAULT_PAGE_SQLID = ".+ByPage$";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement)args[0];
        if(ms.getId().matches(DEFAULT_PAGE_SQLID)){
            Class<? extends MappedStatement> clazz = ms.getClass();
            Field flushCacheRequired = clazz.getDeclaredField("flushCacheRequired");
            flushCacheRequired.setAccessible(true);
            flushCacheRequired.set(ms, true);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        //do nothing
    }
}
