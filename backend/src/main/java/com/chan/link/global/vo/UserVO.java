package com.chan.link.global.vo;

import com.chan.link.global.entity.Authority;
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
    @Column(name = "userSeq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq; //유저 번호

    @Column(name ="userEmail", length = 50, unique = true)
    private String email; // 유저 이메일

    @JsonIgnore
    @Column(name = "userPw", length = 500)
    private String pw; // 유저 패스워드


    @Column(name = "userGender", length = 10)
    private String gender; // 유저 성별
    @Column(name = "userName", length = 10)
    private String name; // 유저 이름
    @Column(name = "userPhone", length = 10)
    private String phone; // 유저 전화번호
    @Column(name = "userNickname", length = 10)
    private String nickname; // 유저 닉네임

    @JsonIgnore
    @Column(name = "userActivated")
    private boolean activated;
    @Column(name = "userCreateAt")
    private LocalDateTime createAt; // 유저 생성일
    @Column(name = "userModifiedAt")
    private LocalDateTime modifiedAt; // 유저 정보 변경일

    @ManyToMany
    @JoinTable(
            name = "userAuthority",
            joinColumns = {@JoinColumn(name= "userSeq", referencedColumnName = "userSeq")},
            inverseJoinColumns = {@JoinColumn(name = "authorityName", referencedColumnName =  "authorityName")}
    )
    private Set<Authority> authorities;


}
