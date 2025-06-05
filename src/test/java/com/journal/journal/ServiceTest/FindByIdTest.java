package com.journal.journal.ServiceTest;

import com.journal.journal.entity.JournalEntry;
import com.journal.journal.repo.JournalEntryRepo;
import com.journal.journal.service.JournalEntryService;
import com.journal.journal.service.UserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FindByIdTest {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @Test
    public void findJournal(){
        List<JournalEntry> list = journalEntryRepo.findAllByUserName("test");
        JournalEntry test = list.getFirst();
        ObjectId id =test.getId();
        Optional<Object> J = journalEntryService.findById(id);
        assertFalse(J.isEmpty());
    }
}



