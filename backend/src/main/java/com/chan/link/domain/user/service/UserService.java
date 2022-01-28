package com.chan.link.domain.user.service;

import com.chan.link.domain.user.dto.UserDto;
import com.chan.link.global.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User signService(UserDto.Sign signDto); // Sign Service
    User userUpdateService(UserDto.Update userUpdateDto); // User Update Service
    List<User> TestUserAll(); // User All select Test
    String tokenGenerationService(UserDto.Login loginDto);
    boolean emailCheck(String email); // email check Service
    Optional<User> getMyInfo();
    Optional<User> getUserInfo(String email);
}
