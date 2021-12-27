package com.chan.link.domain.user.dto;

import com.chan.link.global.entity.Authority;
import com.chan.link.global.vo.UserVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//회원가입 시 요청 데이터
public class SignDto {

    @NotNull
    private String email; //이메일

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String pw; //비밀번호

    @NotNull
    private String phone; // 전화번호

    @NotNull
    private String name; // 이름

    @NotNull
    private String gender; // 성별

    @NotNull
    private String nickname; // 닉네임

    public void UserPasswordEncoder(String pw){
        this.pw = pw;
    }
    public UserVO toUser(SignDto signDto){
        LocalDateTime dateTime = LocalDateTime.now(); //현재시간 -> created modified 넣기
        //유저 권한만 로그인

        Authority authority = Authority.builder().authorityName("ROLE_USER").build();
        String uuid = UUID.randomUUID().toString(); //uuid 생성
        return UserVO.builder().userId(uuid)
                .userEmail(signDto.getEmail())
                .userPw(signDto.getPw())
                .userGender(signDto.getGender())
                .userName(signDto.getName())
                .userPhone(signDto.getPhone())
                .userNickname(signDto.getNickname())
                .authorities(Collections.singleton(authority))
                .userCreateAt(dateTime)
                .userModifiedAt(dateTime)
                .userActivated(true)
                .build();
    }
}
