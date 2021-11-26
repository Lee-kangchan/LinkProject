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
public class LinkVO {
    @JsonIgnore
    @Id
    @Column(name = "linkSeq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkSeq; //링크 번호


    @Column(name = "linkTitle", length = 100)
    private String title;


    @Column(name = "linkImage", length = 300)
    private String image;


    @Column(name = "linkContent", length = 100)
    private String content;

    @Column(name = "linkSecret")
    private int secret;

    @Column(name = "linkRecommend")
    private int recommend;

    @Column(name= "userSeq")
    private Long userSeq;

    @Column(name = "linkCreateAt")
    private LocalDateTime createAt;

    @Column(name = "linkModifiedAt")
    private LocalDateTime modifiedAt;

    @OneToMany(targetEntity = HashTag.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "linkSeq")
    private Set<HashTag> hashTag;

    @Transient
    private int followCnt;


}
