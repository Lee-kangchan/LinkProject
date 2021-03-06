package com.chan.link.global.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "follow_link")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 링크 조회한 데이터 (미구현)
public class FollowLink {

    @Id
    @Column(name = "follow_link_seq")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followLinkSeq;

    @Column(name = "follow_link_user_id")
    private String userId;

    @Column(name = "follow_link_post_id")
    private String postId;

}
