package com.journal.journal.ServiceTest;

import com.journal.journal.entity.User;
import com.journal.journal.repo.UserRepo;
import com.journal.journal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class DeleteUserTest {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @Test
    public void deleteUserTest(){
        userService.deleteUser("test");
        User test = userRepo.findByUserName("test");
        assertNull(test);

    }
}
