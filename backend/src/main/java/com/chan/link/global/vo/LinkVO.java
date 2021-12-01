package com.chan.link.global.vo;


import com.chan.link.global.entity.HashTag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "LINK")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 링크 데이터
public class LinkVO {
    @JsonIgnore
    @Id
    @Column(name = "linkSeq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkSeq; //링크 번호


    @Column(name = "linkTitle", length = 100)
    private String title; // 링크 제목


    @Column(name = "linkImage", length = 300)
    private String image; // 링크 이미지


    @Column(name = "linkContent", length = 100)
    private String content; // 링크 내용

    @Column(name = "linkSecret")
    private int secret; // 링크 보여주기/가리기

    @Column(name = "linkRecommend")
    private int recommend; // 추천수

    @Column(name= "userSeq")
    private Long userSeq; // 사용자 번호 (링크를 저장한 사용자)

    @Column(name = "linkCreateAt")
    private LocalDateTime createAt; // 생성일

    @Column(name = "linkModifiedAt")
    private LocalDateTime modifiedAt; // 수정일

    @OneToMany(targetEntity = HashTag.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "linkseq")
    private Set<HashTag> hashTag; // 해쉬태그 리스트 정보

    @Transient
    private int followCnt; // 팔로우 수


}
