package com.chan.link.user;

import com.chan.link.domain.user.dto.UserDto;
import com.chan.link.domain.user.repository.UserRepository;
import com.chan.link.domain.user.service.UserService;
import com.chan.link.global.vo.UserVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private UserDto.Update updateDto;
    private UserDto.Sign signDto;
    private UserDto.Login loginDto;

    @BeforeEach
    public void before() {
        loginDto = UserDto.Login.builder()
                .email("test1@test.com")
                .password("1111").build();

        signDto = UserDto.Sign.builder()
                .email("test1@test.com")
                .pw("1111")
                .gender("men")
                .nickname("testMen")
                .name("test")
                .phone("010-0011-1112")
                .build();

        updateDto = UserDto.Update.builder()
                .gender("women")
                .pw("2222")
                .phone("010-0000-1111")
                .build();
    }
    @Test
    void contextLoads() {
    }

    // null을 해야 처리 될까 ? isEmpty로 처리를 해야 될까?
    @Test
    void isEmptyTest() {
        UserVO vo2 = new UserVO();
    }

    @Test
    void bindingObject(){

    }
}
