package com.journal.journal.repo;
import com.journal.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {

    User findByUserName(String uerName);
    void deleteByUserName(String userName);


}
