package com.chan.link.user;

import com.chan.link.domain.user.dto.TokenDto;
import com.chan.link.domain.user.dto.UserDto;
import com.chan.link.domain.user.repository.UserRepository;
import com.chan.link.domain.user.service.AuthService;
import com.chan.link.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith( SpringRunner.class )
@SpringBootTest
@Transactional
public class UserLoginTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private UserDto.Login loginDto;
    private UserDto.Login loginDto2;
    private UserDto.Login loginDto3;

    private TokenDto tokenDto;
    @BeforeEach
    public void before() {
        loginDto = UserDto.Login.builder()
                .email("abcd@abcd.com")
                .password("1234").build();

        loginDto2 = UserDto.Login.builder()
                .email("abcdf@abcd.com")
                .password("1234").build();

        loginDto3 = UserDto.Login.builder()
                .email("abcd@abcd.com")
                .password("1111").build();

    }

    @Test
    @DisplayName("로그인 성공")
    void loginSuccessTest(){
        userService.tokenGenerationService(loginDto);
    }

    @Test
    @DisplayName("로그인 실패 (이메일)")
    void loginEmailFailTest(){
        userService.tokenGenerationService(loginDto2);
    }

    @Test
    @DisplayName("로그인 실패 (패스워드)")
    void loginPasswordFailTest(){
        userService.tokenGenerationService(loginDto3);
    }
}
