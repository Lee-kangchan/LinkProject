package com.chan.link.global.util;

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
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            log.info("Security Context 정보 없음");
            return Optional.empty();
        }
        String email = null;
        if(authentication.getPrincipal() instanceof UserDetails){
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            email = springSecurityUser.getUsername();
        } else if(authentication.getPrincipal() instanceof String){
            email = (String) authentication.getPrincipal();
        }
        return Optional.ofNullable(email);
    }
}
