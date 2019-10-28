package com.zhxshark.personmessage.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhxshark.personmessage.commen.result.JsonResult;
import com.zhxshark.personmessage.service.GithubAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

/**
 * github登录三步骤:
 * 1.用户登录时重定向到github，登录成功之后回调redirect-uri
 * 地址：https://github.com/login/oauth/authorize
 * 指定的六个参数(client_id、redirect_uri、login、scope、state、allow_signup)中，
 * redirect_url在第一步创建OAuth App时已指定，即callbackURL，而client_id在OAuth App创建成功后会自动生成。
 * 返回: code和state
 * 2.根据code去请求access_token
 * 地址：https://github.com/login/oauth/access_token
 * 返回: access_token
 * 3.根据access_token去获取用户的具体信息
 * 地址：https://api.github.com/user
 */

/**
 * @author zhuxin
 * @date 2019/10/12 16:11
 */
@Configuration
public class GithubAuthServiceImpl implements GithubAuthService {

    private static Logger logger = LoggerFactory.getLogger(GithubAuthServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.client.clientId}")
    private  String clientId;

    @Value("${github.client.clientSecret}")
    private  String clientSecret;

    @Value("${github.client.accessTokenUri}")
    private  String accessTokenUri;

    @Value("${github.client.userAuthorizationUri}")
    private  String userAuthorizationUri;

    @Value("${github.client.callbackUrl}")
    private  String callbackUrl;

    @Value("${github.client.registeredRedirectUri}")
    private  String rregisteredRedirectUri;

    @Value("${github.client.grantType}")
    private  String grantType;

    @Value("${github.resource.userInfoUri}")
    private  String userInfoUri;


    @Override
    public JsonResult getAccessToken(String code) {
        String url = String.format(accessTokenUri, grantType, code, callbackUrl, clientId, clientSecret);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();
        Map<String,Object> resp;
        try{
            resp = restTemplate.getForObject(uri, Map.class);
        }catch (Exception e){
            logger.error("Github获取access_token失败,code不正确或者已经过期,错误信息:"+e.getMessage());
            return new JsonResult(JsonResult.ERROR,"","获取Token失败，Code不正确或者已经过期");
        }
        //如果返回中存在access_token
        if(resp != null && resp.containsKey("access_token")){
            String access_token = resp.get("access_token").toString();
            return new JsonResult(access_token);
        }
        logger.error("Github获取access_token失败,code不正确或者已经过期");
        return new JsonResult(JsonResult.ERROR,"","获取Token失败，Code不正确或者已经过期");
    }

    @Override
    public JsonResult getOpenId(String accessToken) {
        return null;
    }

    @Override
    public JsonResult refreshToken(String code) {
        return null;
    }

    @Override
    public String getAuthorizationUrl() {
        String url = String.format(userAuthorizationUri, clientId, callbackUrl, grantType);
        return url;
    }

    @Override
    public JsonResult getUserInfo(String accessToken) {
        String url = String.format(userInfoUri,accessToken);
        JSONObject obj;
        try {
            obj = restTemplate.getForObject(url,JSONObject.class);
        }catch (Exception e){
            logger.error("Github获取用户信息失败,错误信息:"+e.getMessage());
            return new JsonResult(JsonResult.ERROR,"","获取用户信息失败，Code不正确或者已经过期");
        }
        return  new JsonResult(obj);
    }

}
