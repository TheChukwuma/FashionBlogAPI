package com.charlesbabbage.fashionblogapi.controller;

import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.enums.LoginEnum;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@AllArgsConstructor
@RestController
@RequestMapping
public class UserController {

    private final UserService userService;
    private final UserRepository userRepo;

    @PostMapping("/account/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO, HttpSession session) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (userRepo.findByUsername(userDTO.getUsername()) != null) {
            return new ResponseEntity<User>(userService.register(userDTO), HttpStatus.CONFLICT);
        } else{
            return new ResponseEntity<User>(userService.register(userDTO), HttpStatus.OK);
        }
    }

    @GetMapping("/account/login")
    public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if ((userService.login(username, password)).equals(LoginEnum.SUCCESS.name())) {
            return new ResponseEntity<String>(userService.login(username, password), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>(userService.login(username, password), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> sendPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<Post>(userService.uploadPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") String id){
        return new ResponseEntity<Post>(userService.getPost(id), HttpStatus.OK);
    }

}
