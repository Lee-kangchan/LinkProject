package com.chan.link.vo;

import com.chan.link.entity.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate // 변경한 필드만 대응
public class UserVO {
    @JsonIgnore
    @Id
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq; //유저 번호

    @Column(name ="email", length = 50, unique = true)
    private String email; // 유저 이메일

    @JsonIgnore
    @Column(name = "pw", length = 100)
    private String pw; // 유저 패스워드


    private String gender; // 유저 성별
    private String name; // 유저 이름
    private String phone; // 유저 전화번호
    private String nickname; // 유저 닉네임

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;
    private LocalDateTime create_at; // 유저 생성일
    private LocalDateTime modified_at; // 유저 정보 변경일

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name= "user_seq", referencedColumnName = "seq")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName =  "authority_name")}
    )
    private Set<Authority> authorities;


}
