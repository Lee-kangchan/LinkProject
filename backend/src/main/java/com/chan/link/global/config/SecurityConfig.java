package com.chan.link.global.config;


import com.chan.link.global.jwt.JwtAccessDeniedHandler;
import com.chan.link.global.jwt.JwtAuthenticationEntryPoint;
import com.chan.link.global.jwt.JwtSecurityConfig;
import com.chan.link.global.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize 어노테이션을 메소드단위로 추가하기 위해서
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler){
        this.tokenProvider = tokenProvider;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico"); // 무시하기
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // token 방식이여서 csrf 차단

                .exceptionHandling() // exception 핸들링
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and() // h2 console 설정
                .headers()
                .frameOptions()
                .sameOrigin()

                .and() // 세션 사용 X
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/user/authenticate").permitAll() //로그인 -> 누구나 접근 가능
                .antMatchers("/user/sign").permitAll() // 회원가입 -> 누구나 접근 가능
                .antMatchers("/link/all").permitAll() // 링크 모두 조회 -> 누구나 접근 가능
                .antMatchers("/link/best").permitAll() // 링크 베스트 조회 -> 누구나 접근 가능
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
        ;
    }
}
