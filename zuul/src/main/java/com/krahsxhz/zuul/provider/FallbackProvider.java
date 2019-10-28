package com.krahsxhz.zuul.provider;

import com.alibaba.fastjson.JSON;
import com.krahsxhz.zuul.commen.result.ExceptionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhuxin
 * @date 2019/10/15 13:45
 */
@Component
public class FallbackProvider implements org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider {

    private static final Logger logger = LoggerFactory.getLogger(FallbackProvider.class);

    /**
     * 返回服务的id，如果支持所有服务都回退，则return null 获 return *
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                logger.warn("进入fallbackProvider");
                ExceptionResult exceptionResult = new ExceptionResult(9999,"系统错误，请联系管理员","系统错误，请联系管理员",route);
                return new ByteArrayInputStream(JSON.toJSONString(exceptionResult).getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                return null;
            }
        };
    }
}
