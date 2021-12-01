package com.chan.link.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

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
}
