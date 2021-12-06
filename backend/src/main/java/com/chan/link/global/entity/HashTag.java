package com.chan.link.global.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "hashtag")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//해쉬 태그 정보
public class HashTag {
    @Id
    @Column(name = "hashtag_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashtagSeq; // 번호

    @Column(name = "hashtag_name")
    private String hashtagName; // 태그 이름

    @Column(name = "hashtag_post_id")
    private String postId;
}
