package com.chan.link.user;

import com.chan.link.domain.user.dto.UserDto;
import com.chan.link.domain.user.repository.UserRepository;
import com.chan.link.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserSignTest {

    @Autowired
    private UserService userService;

    private UserDto.Sign signDto;

    private UserDto.Sign alreadySignDto;
    @BeforeEach
    public void before() {
        signDto = UserDto.Sign.builder()
                .email("test1@test.com")
                .pw("1111")
                .gender("men")
                .nickname("testMen")
                .name("test")
                .phone("010-0011-1112")
                .build();

        alreadySignDto = UserDto.Sign.builder()
                .email("admin@admin.com")
                .pw("1111")
                .gender("men")
                .nickname("testMen")
                .name("test")
                .phone("010-0011-1112")
                .build();

    }

    @Test
    @DisplayName("회원가입 성공")
    void signSuccessTest(){
        userService.signService(signDto);
    }
    @Test
    @DisplayName("회원가입 중복 처리")
    void continuitySignTest(){
        userService.signService(signDto);
        userService.signService(signDto);
    }

    @Test
    @DisplayName("가입된 이메일 회원가입")
    void loginPasswordFailTest(){
        userService.signService(alreadySignDto);
    }
}
