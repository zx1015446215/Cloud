package com.krahsxhz.zuul.filter;

import com.krahsxhz.zuul.commen.exception.GateWayException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @author zhuxin
 * @date 2019/10/15 10:39
 */
@Component
public class AccessTimeFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(AccessTimeFilter.class);

    /**
     * 当前时间
     */
    private static final LocalTime NOW = LocalTime.now();

    /**
     * 零点
     */
    private static final LocalTime ZERO_CLOCK = LocalTime.of(0,0);

    /**
     * 二十点
     */
    private static final LocalTime TWENTY_CLOCK = LocalTime.of(20,0);

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.DEBUG_FILTER_ORDER - 5;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.warn("进入run");
//        if(NOW.isAfter(ZERO_CLOCK) && NOW.isBefore(TWENTY_CLOCK)){
//            throw  new GateWayException(200,"服务器异常");
//        }
        return null;
    }
}
