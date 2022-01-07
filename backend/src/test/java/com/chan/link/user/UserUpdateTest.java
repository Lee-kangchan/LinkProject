package com.chan.link.user;

import com.chan.link.domain.user.dto.UserDto;
import com.chan.link.domain.user.repository.UserRepository;
import com.chan.link.domain.user.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserUpdateTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private UserDto.Update updateDto;

    @BeforeEach
    public void before() {
        updateDto = UserDto.Update.builder()
                .gender("women")
                .pw("2222")
                .phone("010-0000-1111")
                .build();
    }

}
