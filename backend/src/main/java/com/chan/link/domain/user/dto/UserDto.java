package com.chan.link.domain.user.dto;

import com.chan.link.global.entity.Authority;
import com.chan.link.global.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

public class UserDto {


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    //로그인 시 요청 데이터
    public static class Login {
        private String email; //이메일
        private String password; // 비밀번호
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    //회원가입 데이터
    public static class Sign {
        private String email; //이메일
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String pw; //비밀번호
        private String phone; // 전화번호
        private String name; // 이름
        private String gender; // 성별
        private String nickname; // 닉네임
        public void UserPasswordEncoder(String pw){
            this.pw = pw;
        }
        public User toUser(){
            LocalDateTime dateTime = LocalDateTime.now(); //현재시간 -> created modified 넣기
            //유저 권한만 로그인
            Authority authority = Authority.builder().authorityName("ROLE_USER").build();
            String uuid = UUID.randomUUID().toString(); //uuid 생성
            return User.builder().userId(uuid)
                    .userEmail(email)
                    .userPw(pw)
                    .userGender(gender)
                    .userName(name)
                    .userPhone(phone)
                    .userNickname(nickname)
                    .authorities(Collections.singleton(authority))
                    .userCreateAt(dateTime)
                    .userModifiedAt(dateTime)
                    .userActivated(true)
                    .build();
        }
    }


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    //유저 업데이트 데이터
    public static class Update {

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String pw; //비밀번호
        private String phone; // 전화번호
        private String name; // 이름
        private String gender; // 성별
        private String nickname; // 닉네임
        public void UserPasswordEncoder(String pw){
            this.pw = pw;
        }
        public User toUser(User user){
            LocalDateTime dateTime = LocalDateTime.now(); //현재시간 -> created modified 넣기
            user.setUserPw(pw);
            user.setUserPhone(phone);
            user.setUserName(name);
            user.setUserGender(gender);
            user.setUserNickname(nickname);
            user.setUserModifiedAt(dateTime);
            return user;
        }
    }

}
