package com.chan.link.global.entity;

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
//해쉬 태그 정보
public class HashTag {
    @Id
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq; // 번호

    @Column(name = "name")
    private String name; // 태그 이름
}
