package com.chan.link.user;

import com.chan.link.domain.user.dto.UserDto;
import com.chan.link.domain.user.repository.UserRepository;
import com.chan.link.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserSignTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private UserDto.Sign signDto;

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

    }
}
