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
public class HashTag {
    @Id
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "name")
    private String name;
}
