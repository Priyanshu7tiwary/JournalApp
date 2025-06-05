package com.journal.journal.repo;
import com.journal.journal.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {
    void deleteByUserName(String username);
    List<JournalEntry> findAllByUserName(String username);

}
