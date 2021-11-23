package com.chan.link.domain.user.service;

import com.chan.link.domain.user.dto.SignDto;
import com.chan.link.domain.user.dto.UserUpdateDto;
import com.chan.link.global.vo.UserVO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserVO loginService(UserVO userVO); // Login Service
    UserVO signService(SignDto signDto); // Sign Service
    UserVO userUpdateService(UserUpdateDto userUpdateDto, String email); // User Update Service
    List<UserVO> TestUserAll(); // User All select Test
    boolean emailCheck(String email); // email check Service
    Optional<UserVO> getMyInfo();
    Optional<UserVO> getUserInfo(String email);
}
