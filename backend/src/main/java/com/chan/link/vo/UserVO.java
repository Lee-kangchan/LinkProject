package com.chan.link.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name= "USER")
public class UserVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq; //유저 번호
    private String email; // 유저 이메일
    private String pw; // 유저 패스워드
    private String gender; // 유저 성별
    private String name; // 유저 이름
    private String phone; // 유저 전화번호
    private String nickname; // 유저 닉네임
    private Date create_at; // 유저 생성일
    private Date modified_at; // 유저 정보 변경일


    public UserVO() {
    }

    public UserVO (String email, String pw, String gender, String name, String phone, String nickname){
        this.email = email;
        this.pw = pw;
        this.gender = gender;
        this. name = name;
        this.phone = phone;
        this.nickname =nickname;
    }
    public UserVO(Long seq, String email, String pw, String gender, String name, String phone, String nickname, Date create_at, Date modified_at) {
        this.seq = seq;
        this.email = email;
        this.pw = pw;
        this.gender = gender;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
        this.create_at = create_at;
        this.modified_at = modified_at;
    }

    public UserVO(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }


    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }
}
