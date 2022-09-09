package com.charlesbabbage.fashionblogapi.controller;

import com.charlesbabbage.fashionblogapi.dto.LoginRequestDTO;
import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.service.CommentService;
import com.charlesbabbage.fashionblogapi.service.LikeService;
import com.charlesbabbage.fashionblogapi.service.PostService;
import com.charlesbabbage.fashionblogapi.service.UserService;
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

    private final PostService postService;

    private final CommentService commentService;

    private final LikeService likeService;

    @PostMapping("/account/register")
    public ResponseEntity<APIResponse> register(@RequestBody UserDTO userDTO, HttpSession session) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return userService.register(userDTO);
    }

    @GetMapping("/account/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return userService.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }


}
