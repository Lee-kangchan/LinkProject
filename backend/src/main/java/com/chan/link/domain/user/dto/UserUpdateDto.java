package com.chan.link.domain.user.dto;

import com.chan.link.global.vo.UserVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//유저 업데이트 데이터
public class UserUpdateDto {

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
    public void toUser(UserVO user, UserUpdateDto userUpdateDto){
        LocalDateTime dateTime = LocalDateTime.now(); //현재시간 -> created modified 넣기

        user.builder().userPw(userUpdateDto.getPw())
                .userName(userUpdateDto.getName())
                .userPhone(userUpdateDto.getPhone())
                .userNickname(userUpdateDto.getNickname())
                .userModifiedAt(dateTime)
                .build();
    }
}
