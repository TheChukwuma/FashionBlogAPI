package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserService {

    ResponseEntity<APIResponse> register(UserDTO userDTO) throws NoSuchAlgorithmException, InvalidKeySpecException;

    ResponseEntity<APIResponse> login(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;



}
