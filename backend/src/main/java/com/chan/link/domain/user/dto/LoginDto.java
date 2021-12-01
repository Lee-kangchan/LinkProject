package com.chan.link.domain.user.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//로그인 시 요청 데이터
public class LoginDto {
    @NotNull
    private String email; //이메일

    @NotNull
    private String password; // 비밀번호
}
