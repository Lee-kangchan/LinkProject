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


    // null을 해야 처리 될까 ? isEmpty로 처리를 해야 될까?
    @Test
    void isEmptyTest() {
        UserVO vo2 = new UserVO();
    }

    @Test
    void bindingObject(){

    }
}
