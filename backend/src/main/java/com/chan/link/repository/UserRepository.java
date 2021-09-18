package com.chan.link.repository;

import com.chan.link.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {
    UserVO findByEmailAndPw(String email, String pw); // email and password 검증
    UserVO findByEmail(String email); // email 체크

}
