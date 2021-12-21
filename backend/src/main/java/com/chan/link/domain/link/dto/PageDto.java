package com.chan.link.domain.link.dto;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {
    private int page;
    private int size;
}
