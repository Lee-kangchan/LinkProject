package com.chan.link.global.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "post")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 링크 데이터
public class Post {
//    @JsonIgnore
    @Id
    @Column(name = "post_id")
    private String postId; //링크 UUID

    @Column(name = "post_title", length = 100)
    private String postTitle; // 링크 제목

    @Column(name = "post_image", length = 300)
    private String postImage; // 링크 이미지

    @Column(name = "post_content", length = 100)
    private String postContent; // 링크 내용

    @Column(name = "post_secret")
    private int postSecret; // 링크 보여주기/가리기

    @Column(name = "post_recommend")
    private int postRecommend; // 추천수

    @Column(name= "post_user_id")
    private String postUserId; // 사용자 번호 (링크를 저장한 사용자)

    @Column(name = "post_created_at")
    private LocalDateTime postCreateAt; // 생성일

    @Column(name = "post_modified_at")
    private LocalDateTime postModifiedAt; // 수정일

    @OneToMany(targetEntity = HashTag.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hashtag_post_id")
    private Set<HashTag> hashTag; // 해쉬태그 리스트 정보

    @OneToMany(targetEntity = FollowLink.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="follow_link_post_id")
    private Set<FollowLink> postFollowLink; // 수정일

    @Column(name = "post_link")
    private String postLink;


}
