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
    @Column(name = "LINK_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq; //유저 번호


    @Column(name = "LINK_title", length = 100)
    private String title;


    @Column(name = "LINK_image", length = 300)
    private String image;


    @Column(name = "LINK_content", length = 100)
    private String content;

    @Column(name = "LINK_secret")
    private int secret;

    @Column(name = "LINK_recommend")
    private int recommend;

    @Column(name = "LINK_create_at")
    private LocalDateTime create_at;

    @Column(name = "LINK_modified_at")
    private LocalDateTime modified_at;

    @ManyToOne(targetEntity=UserVO.class, fetch=FetchType.LAZY) // (1)
    @JoinColumn(name="Link_seq") // (2)
    private UserVO user_seq;

    @OneToMany(targetEntity = HashTag.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "Link_seq")
    private Set<HashTag> hashTag;

    @ManyToMany
    @JoinTable(
            name = "followlink",
            joinColumns = {@JoinColumn(name= "link_seq", referencedColumnName = "link_seq")},
            inverseJoinColumns = {@JoinColumn(name = "user_seq", referencedColumnName =  "user_seq")}
    )
    private List<UserVO> followUser;
}
