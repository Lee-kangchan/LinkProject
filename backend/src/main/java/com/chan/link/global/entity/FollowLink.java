package com.chan.link.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "HASHTAG")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 링크 조회한 데이터 (미구현)
public class FollowLink {

    @Id
    @Column(name = "followlinkseq")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followLinkSeq;

    @Column(name = "userseq")
    private Long userSeq;

    @Column(name = "linkseq")
    private Long linkSeq;

}
