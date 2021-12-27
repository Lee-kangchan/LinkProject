package com.chan.link.global.vo;

import com.chan.link.global.entity.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate // 변경한 필드만 대응
public class UserVO {
    @Id
    @Column(name = "user_id")
    private String userId; //유저 UUID

    @Column(name ="user_email", length = 50, unique = true)
    private String userEmail; // 유저 이메일

    @JsonIgnore
    @Column(name = "user_pw", length = 500)
    private String userPw; // 유저 패스워드


    @Column(name = "user_gender", length = 10)
    private String userGender; // 유저 성별
    @Column(name = "user_name", length = 10)
    private String userName; // 유저 이름
    @Column(name = "user_phone", length = 10)
    private String userPhone; // 유저 전화번호
    @Column(name = "user_nickname", length = 10)
    private String userNickname; // 유저 닉네임

    @JsonIgnore
    @Column(name = "user_activated")
    private boolean userActivated; // 공개 비공개
    @Column(name = "user_created_at")
    private LocalDateTime userCreateAt; // 유저 생성일
    @Column(name = "user_modified_at")
    private LocalDateTime userModifiedAt; // 유저 정보 변경일

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name= "user_authority_user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_authority_authority_name", referencedColumnName =  "authority_name")}
    )
    private Set<Authority> authorities; // 권한 정보


}
