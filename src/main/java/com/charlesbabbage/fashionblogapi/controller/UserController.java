package com.charlesbabbage.fashionblogapi.controller;

import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.enums.LoginEnum;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO){
        return new ResponseEntity<User>(userService.register(userDTO), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") String username,@RequestParam("password") String password){
        if (userService.login(username, password).equals(LoginEnum.SUCCESS.name())) {
            return new ResponseEntity<String>(userService.login(username, password), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>(userService.login(username, password), HttpStatus.NOT_FOUND);
        }
    }

}
