package com.journal.journal.service;

import com.journal.journal.entity.User;
import com.journal.journal.producer.RabbitMqProducer;
import com.journal.journal.repo.JournalEntryRepo;
import com.journal.journal.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RabbitMqProducer rabbitMq;
    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private RedisService redisService;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List getAll(){
        List list = redisService.get("AllUser");
        if(list!=null){
            log.info("[REDIS CACHE HIT] Key: {}");

            return list;
        }
        else {
            list = userRepo.findAll();
            redisService.set("AllUser", list, 60000L);
            return list;
        }
    }
    public boolean deleteId(ObjectId id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public User findByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }
    public void deleteUser(String username){
        journalEntryRepo.deleteByUserName(username);
        userRepo.deleteByUserName(username);
    }
    public void saveUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
        } catch (Exception e) {
            log.error("Error Ohiushiduhad",e);
        }
    }
    public void saveJournal(User user) {
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);
    }
}
