package com.charlesbabbage.fashionblogapi.controller;

import com.charlesbabbage.fashionblogapi.dto.CommentDTO;
import com.charlesbabbage.fashionblogapi.dto.PostDTO;
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
@RequestMapping
public class UserController {

    private final UserService userService;

    private final PostService postService;

    private final CommentService commentService;

    private final LikeService likeService;

    @PostMapping("/account/register")
    public ResponseEntity<APIResponse> register(@RequestBody UserDTO userDTO, HttpSession session) throws NoSuchAlgorithmException, InvalidKeySpecException {
            return userService.register(userDTO);
    }

    @GetMapping("/account/login")
    public ResponseEntity<APIResponse> login(@RequestParam("username") String username, @RequestParam("password") String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
            return userService.login(username, password);
    }

    @GetMapping("/account/profile/{id}")
    public ResponseEntity<APIResponse> getUser(@PathVariable("id") String user_id){
        return userService.getUser(user_id);
    }


    @PostMapping("/posts")
    public ResponseEntity<APIResponse> sendPost(@RequestBody PostDTO postDTO){
        return postService.uploadPost(postDTO);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<APIResponse> getPost(@PathVariable("id") String id){
        return postService.getPost(id);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<APIResponse> deletePost(@PathVariable("id") String id){
        return postService.deletePost(id);
    }

    @PostMapping("/posts/comments")
    public ResponseEntity<APIResponse> makeComment(@RequestBody CommentDTO commentDTO){
        return commentService.createComment(commentDTO);
    }

    @GetMapping("/posts/comments/{id}")
    public ResponseEntity<APIResponse> getComment(@PathVariable String id){
        return commentService.getComment(id);
    }

    @PutMapping("/posts/comments/{id}")
    public ResponseEntity<APIResponse> editComment(@RequestBody CommentDTO commentDTO, @PathVariable String id){
        return commentService.editComment(commentDTO, id);
    }

    @DeleteMapping("/posts/comments/{id}")
    public ResponseEntity<APIResponse> deleteComment(@PathVariable("id") String id){
        return commentService.deleteComment(id);
    }


    @PostMapping("/posts/likes")
    public ResponseEntity<APIResponse> likePost(@RequestParam String user_id, String post_id){
        return likeService.likeAPost(user_id, post_id);
    }


    @PostMapping("/posts/comments/likes")
    public ResponseEntity<APIResponse> likeComment(@RequestParam String user_id, String comment_id){
        return likeService.likeAComment(user_id, comment_id);
    }

}
