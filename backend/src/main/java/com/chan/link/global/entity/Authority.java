package com.chan.link.global.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//권한 이름 (ROLE_ADMIN, ROLE_USER)
public class Authority {
    @Id
    @Column(name = "authorityname", length = 50)
    private String authorityName;
}
