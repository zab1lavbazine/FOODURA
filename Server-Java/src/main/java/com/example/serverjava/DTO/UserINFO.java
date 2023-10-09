package com.example.serverjava.DTO;

import com.example.serverjava.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserINFO {
    private UUID id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;


    public UserINFO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
