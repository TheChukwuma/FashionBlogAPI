package com.charlesbabbage.fashionblogapi.controller;

import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class UserController {
    private final UserService userService;

    @GetMapping("/accounts/profile/{id}")
    public ResponseEntity<APIResponse> getUser(@PathVariable("id") Long user_id){
        return userService.getUser(user_id);
    }

}
