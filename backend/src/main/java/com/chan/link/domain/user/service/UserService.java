package com.chan.link.domain.user.service;

import com.chan.link.domain.user.dto.UserDto;
import com.chan.link.global.vo.UserVO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserVO signService(UserDto.Sign signDto); // Sign Service
    UserVO userUpdateService(UserDto.Update userUpdateDto); // User Update Service
    List<UserVO> TestUserAll(); // User All select Test
    String tokenGenerationService(UserDto.Login loginDto);
    boolean emailCheck(String email); // email check Service
    Optional<UserVO> getMyInfo();
    Optional<UserVO> getUserInfo(String email);
}
