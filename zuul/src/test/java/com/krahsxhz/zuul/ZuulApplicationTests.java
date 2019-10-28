package com.krahsxhz.zuul;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZuulApplicationTests {

    @Test
    public void contextLoads() {
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost:8882/hi?name=zhuxin");
        CloseableHttpResponse response = null;
        try {
            //配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    //设置连接超时时间
                    .setConnectTimeout(5000)
                    //设置请求超时时间
                    .setConnectionRequestTimeout(5000)
                    //socket读写超时时间
                    .setSocketTimeout(5000)
                    //是否允许重定向
                    .setRedirectsEnabled(true).build();
            httpGet.setConfig(requestConfig);
            response = build.execute(httpGet);
            HttpEntity entity = response.getEntity();
            System.out.println("响应内容的长度为:"+entity.getContentLength());
            System.out.println("响应的编码格式:"+entity.getContentEncoding());
            System.out.println("响应的内容为:"+ EntityUtils.toString(entity));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
