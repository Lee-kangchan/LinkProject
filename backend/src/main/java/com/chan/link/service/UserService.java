package com.chan.link.service;

import com.chan.link.vo.UserVO;

import java.util.List;

public interface UserService {
    UserVO loginService(String email, String pw); // Login Service
    boolean signService(String email, String pw, String name, String gender, String phone, String nickname); // Sign Service

    List<UserVO> TestUserAll(); // User All select Test
    boolean emailCheck(String email); // email check Service
}
