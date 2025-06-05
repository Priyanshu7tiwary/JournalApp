package com.journal.journal.dto;


import com.journal.journal.entity.JournalEntry;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Document(collection = "users")
@Data
public class UserDto {

    @NonNull
    private String userName;
    @NonNull
    private String password;
    private List<String> roles;




}
