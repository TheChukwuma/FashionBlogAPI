package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.model.User;

public interface UserService {

    User register(UserDTO userDTO);

    String login(String username, String password);
}
