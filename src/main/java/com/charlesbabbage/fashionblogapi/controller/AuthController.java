package com.charlesbabbage.fashionblogapi.controller;

import com.charlesbabbage.fashionblogapi.dto.AdminDTO;
import com.charlesbabbage.fashionblogapi.dto.LoginRequestDTO;
import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class AuthController {

    private final UserService userService;

    private final AdminService adminService;



    @PostMapping("/accounts/register")
    public ResponseEntity<APIResponse> register(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return userService.register(userDTO);
    }

    @GetMapping("/accounts/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return userService.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }

    @PostMapping("/accounts/admin/register")
    public ResponseEntity<APIResponse> registerAdmin(@RequestBody AdminDTO adminDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return adminService.register(adminDTO);
    }

    @GetMapping("/accounts/admin/login")
    public ResponseEntity<APIResponse> loginAdmin(@RequestBody LoginRequestDTO loginRequestDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return adminService.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }


}
