package com.chan.link.global.util;

import com.chan.link.global.entity.AuthUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
public class SecurityUtil {
    private SecurityUtil() {}

    //SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장
    public static Optional<String> getCurrentMemberId(){
        final Authentication authentication = SecurityContextHolderGetAuthentication();
        String email = null;
        if(authentication.getPrincipal() instanceof AuthUserEntity){
            AuthUserEntity springSecurityUser = (AuthUserEntity) authentication.getPrincipal();
            email = springSecurityUser.getUsername();
        } else if(authentication.getPrincipal() instanceof String){
            email = (String) authentication.getPrincipal();
        }
        return Optional.ofNullable(email);
    }

    //seq만 불러올때 ( Util 메소드를 객체로 담아서 보낼지 따로 보낼 것인지 생각)
    public static String getCurrentUserId(){
        final Authentication authentication = SecurityContextHolderGetAuthentication();
        String id = null;
        if(authentication.getPrincipal() instanceof AuthUserEntity){
            id =  ((AuthUserEntity) authentication.getPrincipal()).getUserId();
        } else if(authentication.getPrincipal() instanceof Long){
            id = (String) authentication.getPrincipal();
        }
        return id;
    }

    private static Authentication SecurityContextHolderGetAuthentication(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            log.info("Security Context 정보 없음");
            return null;
        }
        return authentication;
    }
}
