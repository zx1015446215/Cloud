package com.krahsxhz.zuul.security;

import com.krahsxhz.zuul.provider.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zhuxin
 * @date 2019/9/18 17:44
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/j_spring_security_check")
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login.html").permitAll()
//                .and()
//                .csrf().disable();
        http.authorizeRequests()
                .antMatchers("/api-a/user/register/**").permitAll()
                .antMatchers("/api-a/user/login/**").permitAll()
                .antMatchers("/api-c/**").permitAll()
                .antMatchers("/css/**","/js/**","/image/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().and().httpBasic();
        http.csrf().disable();
    }
}
