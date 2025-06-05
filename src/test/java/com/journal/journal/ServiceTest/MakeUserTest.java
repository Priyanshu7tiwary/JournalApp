package com.journal.journal.ServiceTest;

import com.journal.journal.entity.User;
import com.journal.journal.repo.UserRepo;
import com.journal.journal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MakeUserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Test
    public void createUser(){
        User user = new User();
        user.setUserName("test");
        user.setPassword("test");
        userService.saveUser(user);
        User test = userRepo.findByUserName("test");
        assertNotNull(test);
    }
}
