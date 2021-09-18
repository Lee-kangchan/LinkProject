package com.chan.link;

import com.chan.link.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {


    @Test
    void contextLoads() {
    }

    @Test
    void UserVOisEmptyTest() {
        UserVO vo = new UserVO("abc1234", "password");
        UserVO vo2 = new UserVO();
        
    }
}
