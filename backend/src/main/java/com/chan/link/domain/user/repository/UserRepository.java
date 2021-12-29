package com.chan.link.domain.user.repository;

import com.chan.link.global.vo.UserVO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {
    UserVO findByUserEmailAndUserPw(String email, String pw); // email and password 검증
    Optional<UserVO> findByUserEmail(String email); // email 체크
    Optional<UserVO> findByUserId(String userId);
    @EntityGraph(attributePaths = "authorities")  // EAGER 조회
    Optional<UserVO> findOneWithAuthoritiesByUserEmail(String email); // 이메일을 불러와서 권한 알아내기
}
