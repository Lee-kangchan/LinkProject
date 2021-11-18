package com.chan.link.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String pw;

    @NotNull
    private String phone;

    @NotNull
    private String name;

    @NotNull
    private String gender;

    @NotNull
    private String nickname;
}
