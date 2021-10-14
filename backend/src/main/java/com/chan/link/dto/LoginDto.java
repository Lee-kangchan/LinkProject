package com.chan.link.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotNull
    private String email;

    @NotNull
    private String password;
}
