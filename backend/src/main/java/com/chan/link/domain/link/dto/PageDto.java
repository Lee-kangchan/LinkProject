package com.chan.link.domain.link.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    private int page;
    private int size;
}
