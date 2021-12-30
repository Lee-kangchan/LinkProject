package com.chan.link.domain.link.dto;


import com.chan.link.global.entity.HashTag;
import com.chan.link.global.util.SecurityUtil;
import com.chan.link.global.vo.PostVO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkDto {
    private String title;
    private String image;
    private String content;
    private int secret;
    private String link;
    private Set<String> tagList;

    public PostVO toPost(LinkDto linkDto) {
        String JwtSaveId = SecurityUtil.getCurrentUserId().get();
        String uuid = UUID.randomUUID().toString();
        LocalDateTime localDateTime = LocalDateTime.now();
        Set<HashTag> hashTag = new HashSet<>();

        // HashTag 객체 데이터로 변경하여 리스트 저장
        for(String tag : linkDto.getTagList()){
            HashTag tagData = HashTag.builder()
                    .hashtagName(tag)
                    .postId(uuid)
                    .build();
            hashTag.add(tagData);
        }

        return PostVO.builder()
                .postId(uuid)
                .postTitle(linkDto.getTitle())
                .postContent(linkDto.getContent())
                .postImage(linkDto.getImage())
                .postSecret(1)
                .hashTag(hashTag)
                .postCreateAt(localDateTime)
                .postModifiedAt(localDateTime)
                .postUserId(JwtSaveId)
                .postLink(linkDto.getLink())
                .build();
    }
}
