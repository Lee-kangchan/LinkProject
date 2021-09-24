package com.chan.link.service;

import com.chan.link.vo.UserVO;

import java.util.List;

public interface UserService {
    UserVO loginService(UserVO userVO); // Login Service
    boolean signService(UserVO userVO); // Sign Service

    List<UserVO> TestUserAll(); // User All select Test
    boolean emailCheck(String email); // email check Service
}
