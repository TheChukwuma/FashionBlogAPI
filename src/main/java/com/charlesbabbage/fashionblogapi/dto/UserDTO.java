package com.charlesbabbage.fashionblogapi.dto;


import lombok.Data;

@Data

public class UserDTO {
    String firstName;
    String lastName;
    String username;
    String email;
    String password;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
