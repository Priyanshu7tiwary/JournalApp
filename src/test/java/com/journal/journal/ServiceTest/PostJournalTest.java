package com.journal.journal.ServiceTest;

import com.journal.journal.entity.JournalEntry;
import com.journal.journal.entity.User;
import org.junit.jupiter.api.Test;
import com.journal.journal.repo.JournalEntryRepo;
import com.journal.journal.repo.UserRepo;
import com.journal.journal.service.JournalEntryService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
public class PostJournalTest {


    @Autowired
    private JournalEntryService journalService;

    @Autowired
    private JournalEntryRepo journalRepo;
    @Autowired
    private UserRepo userRepo;


    @Test
    public void JournalEntryTest(){
        User user = userRepo.findByUserName("test");
        JournalEntry J = new JournalEntry();
        J.setContent("one");
        J.setTitle("one");
        journalService.saveEntry(J,"test");
        List<JournalEntry> list;
        list = journalRepo.findAllByUserName("test");
        List<JournalEntry> collect = list.stream().filter(x -> x.equals(J)).toList();
        assertFalse(collect.isEmpty());
    }
}
