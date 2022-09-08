package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.model.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserService {

    User register(UserDTO userDTO) throws NoSuchAlgorithmException, InvalidKeySpecException;

    String login(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;



}
