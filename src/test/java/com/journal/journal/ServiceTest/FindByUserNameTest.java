package com.journal.journal.ServiceTest;

import com.journal.journal.entity.User;
import com.journal.journal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class FindByUserNameTest {

    @Autowired
    private UserService userService;

    @Test
    public void findUserTest(){
    String userName= "test";
    User user = userService.findByUserName(userName);
    assertNull(user);
    }
}
