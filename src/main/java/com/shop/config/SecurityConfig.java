package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //시큐리티 설정을 커스터마이징 할 수 있도록 해줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override //http 요청에 대한 보안을 설정(페이지 권한 설정, 로그인 페이지 설정)
    protected void configure(HttpSecurity http) throws Exception{

    }
//    bean에 등록해야 의존성 주입을 받을 수 있음
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}


}
