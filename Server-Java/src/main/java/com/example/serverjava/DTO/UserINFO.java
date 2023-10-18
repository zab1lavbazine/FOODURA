package com.example.serverjava.DTO;

import com.example.serverjava.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserINFO {
    private long id;
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

    public static List<UserINFO> from(List<User> userList) {
        return userList.stream().map(UserINFO::new).toList();
    }
}
