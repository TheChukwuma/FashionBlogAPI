package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.dto.AdminDTO;
import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AdminService {

    ResponseEntity<APIResponse> register(AdminDTO adminDTO) throws NoSuchAlgorithmException, InvalidKeySpecException;

    ResponseEntity<APIResponse> login(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;


    ResponseEntity<APIResponse> getAdmin(Long admin_id);
}
