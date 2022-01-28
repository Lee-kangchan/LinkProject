package com.chan.link.domain.link.dto;

import com.chan.link.global.entity.Post;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePostDto {
    private PageDto pageDto;
    private List<Post> postVO;
}
