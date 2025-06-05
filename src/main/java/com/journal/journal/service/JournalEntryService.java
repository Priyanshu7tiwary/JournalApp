package com.journal.journal.service;
import com.journal.journal.entity.User;
import com.journal.journal.redis.RedisHashService;
import com.journal.journal.repo.JournalEntryRepo;
import com.journal.journal.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisHashService redisHashService;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            System.out.println("Fetched User: " + user);
            journalEntry.setUserName(userName);
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveJournal(user);
        } catch (Exception e) {
            e.printStackTrace(); //  Print exact exception and line number
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public List<JournalEntry> getAll(String userName){
        List<JournalEntry> list = redisService.get("AllJournalUser");
        if(list!=null){
            return list;
        }
        else {
            list = journalEntryRepo.findAllByUserName(userName);
            redisService.set("AllJournalUser", list, 60000L);
            return list;
        }

    }
    public Optional<Object> findById(ObjectId id){
        Object journal = redisHashService.HmGet(""+ id , new String[]{"title", "content"});

        if (journal != null) {
            return Optional.of(journal);
        } else {
            Optional<Object> journalOptional = Optional.of(journalEntryRepo.findById(id));
            journalOptional.ifPresent(u -> redisHashService.cacheSet( ""+id, u, new String[]{"title","content"})); // 60 seconds in ms
            return journalOptional;
        }
    }
    public void deleteId(ObjectId id,String username) {
        User user = userService.findByUserName(username);
        boolean removed = user.getJournalEntries().removeIf(x->x.getId().equals(id));
        if(removed){
            userService.saveJournal(user);
            journalEntryRepo.deleteById(id);
        }
    }
}
