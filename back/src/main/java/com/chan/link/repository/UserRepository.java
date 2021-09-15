package com.chan.link.repository;

import com.chan.link.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserVO, Long> {
    UserVO findByEmailAndPw(String email, String pw);

}
