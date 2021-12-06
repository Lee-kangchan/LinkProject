package com.chan.link.global.entity;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@Setter
@ToString
//JWT 담길 데이터
public class AuthUserEntity extends User {
    private String userId;
    private String userEmail;

    public AuthUserEntity(String email, String pw, String id, List<GrantedAuthority> grantedAuthorityList){
        super(email, pw, grantedAuthorityList);
        this.userId = id;
        this.userEmail = email;
    }

}
