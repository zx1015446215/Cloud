package com.krahsxhz.zuul.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhxshark.model.ZxRoles;
import com.zhxshark.model.ZxUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxin
 * @date 2019/9/18 17:46
 */
@Configuration
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    private Logger logger = LoggerFactory.getLogger(AuthenticationProvider.class);
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String fdLoginName = (String) auth.getPrincipal();
        String fdPass = (String) auth.getCredentials();
        ZxUser zxUser = new ZxUser();
        zxUser.setFdLoginName(fdLoginName);
        zxUser.setFdPass(fdPass);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://SERVICE-RIBBON/user/login/normal", (JSON)JSON.toJSON(zxUser), String.class);
        JSONObject jsonObject = JSONObject.parseObject(stringResponseEntity.getBody());
        logger.warn(jsonObject.toJSONString());
        String flag = jsonObject.getString("state");

        if(!flag.equals("200")){
            throw new BadCredentialsException(jsonObject.getString("message"));
        }
        //获取用户的角色
        List<ZxRoles> roles = jsonObject.getJSONObject("data").getJSONArray("fdRoles").toJavaList(ZxRoles.class);
        List<GrantedAuthority> role = new ArrayList<>();
        for (int i=0 ; i<roles.size() ; i++){
            role.add(new SimpleGrantedAuthority(roles.get(i).getFdRoleName()));
        }
        return new UsernamePasswordAuthenticationToken(fdLoginName,fdPass,role);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
