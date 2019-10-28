package com.krahsxhz.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @author zhuxin
 * @date 2019/9/20 18:04
 */
@Component
public class PreFilter extends ZuulFilter {
    private static String trueurl;

    static {
        trueurl = "localhost:8080/api-a/";
    }

    @Override
    public String filterType() {
        return "rout";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String url = request.getRequestURI();
        String substring = url.substring(url.lastIndexOf("/")+1);
        currentContext.put(FilterConstants.PROXY_KEY,trueurl+substring);
        return null;
    }
}
