package com.chan.link.vo;


import com.chan.link.entity.HashTag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkVO {
    @JsonIgnore
    @Id
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq; //유저 번호


    @Column(name = "title", length = 100)
    private String title;


    @Column(name = "image", length = 300)
    private String image;


    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "secret")
    private int secret;

    @Column(name = "recommend")
    private int recommend;

    @Column(name = "create_at")
    private LocalDateTime create_at;

    @Column(name = "modified_at")
    private LocalDateTime modified_at;

    @ManyToOne(targetEntity=UserVO.class, fetch=FetchType.LAZY) // (1)
    @JoinColumn(name="seq") // (2)
    private UserVO user_seq;

    @OneToMany(targetEntity = HashTag.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "seq")
    private Set<HashTag> hashTag;

    @ManyToMany
    @JoinTable(
            name = "followlink",
            joinColumns = {@JoinColumn(name= "link_seq", referencedColumnName = "seq")},
            inverseJoinColumns = {@JoinColumn(name = "user_seq", referencedColumnName =  "seq")}
    )
    private List<UserVO> followUser;
}
