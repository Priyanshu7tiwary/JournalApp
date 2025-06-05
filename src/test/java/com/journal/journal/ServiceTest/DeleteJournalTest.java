package com.journal.journal.ServiceTest;

import com.journal.journal.entity.JournalEntry;
import com.journal.journal.repo.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class DeleteJournalTest {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Test
    public void deleteJournal(){
        List<JournalEntry> list = journalEntryRepo.findAllByUserName("test");
        JournalEntry test = list.getFirst();
        ObjectId id =test.getId();
        journalEntryRepo.deleteById(id);
        Optional<JournalEntry> newTest = journalEntryRepo.findById(id);
        assertFalse(newTest.isPresent());
    }
}
