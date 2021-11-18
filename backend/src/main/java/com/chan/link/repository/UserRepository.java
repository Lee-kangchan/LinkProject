package com.chan.link.repository;

import com.chan.link.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {
    UserVO findByEmailAndPw(String email, String pw); // email and password 검증
    Optional<UserVO> findByEmail(String email); // email 체크
    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "authorities")  // EAGER 조회
    Optional<UserVO> findOneWithAuthoritiesByEmail(String email);
}
