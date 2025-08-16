package com.example.learnverse.auth.user;

import com.example.learnverse.auth.modelenum.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Document(collection = "users")
public class AppUser {
    @Id
    private String id;

    private String name;
    private String email;
    private String passwordHash;
    private Role role;

    private Instant createdAt;
}
