package com.krahsxhz.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author zhuxin
 * @date 2019/10/15 11:22
 */
@Component
public class GateWayPostFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(GateWayPostFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.DEBUG_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.warn("进入post的run");
//        RequestContext context = RequestContext.getCurrentContext();
//        InputStream stream = context.getResponseDataStream();
//        String body = null;
//        try {
//            body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        context.setResponseDataStream(new ByteArrayInputStream(body.getBytes()));
        return null;
    }
}
