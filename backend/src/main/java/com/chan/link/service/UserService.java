package com.chan.link.service;

import com.chan.link.dto.SignDto;
import com.chan.link.vo.UserVO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserVO loginService(UserVO userVO); // Login Service
    UserVO signService(SignDto signDto); // Sign Service
    List<UserVO> TestUserAll(); // User All select Test
    boolean emailCheck(String email); // email check Service

    Optional<UserVO> getMyInfo();
    Optional<UserVO> getUserInfo(String email);
}
