package com.chan.link.service;

import com.chan.link.vo.UserVO;

public interface UserService {
    UserVO loginService(String email, String pw);
    void signService(UserVO userVO);

}
