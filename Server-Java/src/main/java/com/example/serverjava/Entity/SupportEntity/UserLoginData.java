package com.example.serverjava.Entity.SupportEntity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginData {
    private String email;
    private String password;
}
