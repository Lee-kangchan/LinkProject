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
public class UserUpdateTest {

    @Autowired
    private UserService userService;

    private UserDto.Update alreadyUpdateDto;
    private UserDto.Update updateDto;

    @BeforeEach
    public void before() {
        updateDto = UserDto.Update.builder()
                .gender("women")
                .pw("2222")
                .name("test2")
                .nickname("test2")
                .phone("010-0000-1111")
                .build();
        alreadyUpdateDto = UserDto.Update.builder()
                .gender("men")
                .pw("1111")
                .phone("010-0000-0000")
                .name("test")
                .nickname("test")
                .build();
    }
    @Test
    @DisplayName("유저 정보 변경 성공")
    void userUpdateSuccessTest(){
        userService.userUpdateService(updateDto);
    }


    @Test
    @DisplayName("똑같은 유저 정보 입력")
    void equalsUserUpdateTest(){
        userService.userUpdateService(alreadyUpdateDto);
    }
}

