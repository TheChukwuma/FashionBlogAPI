package com.charlesbabbage.fashionblogapi.controller;

import com.charlesbabbage.fashionblogapi.dto.CommentDTO;
import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.enums.LoginEnum;
import com.charlesbabbage.fashionblogapi.model.Comment;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.service.CommentService;
import com.charlesbabbage.fashionblogapi.service.PostService;
import com.charlesbabbage.fashionblogapi.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
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

    private final PostService postService;

    private final CommentService commentService;

    @PostMapping("/account/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO, HttpSession session) throws NoSuchAlgorithmException, InvalidKeySpecException {
            return new ResponseEntity<User>(userService.register(userDTO), HttpStatus.OK);
    }

    @GetMapping("/account/login")
    public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
            return new ResponseEntity<String>(userService.login(username, password), HttpStatus.OK);

    }

    @PostMapping("/posts")
    public ResponseEntity<Post> sendPost(@RequestBody PostDTO postDTO){
        return new ResponseEntity<Post>(postService.uploadPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") String id){
        return new ResponseEntity<Post>(postService.getPost(id), HttpStatus.OK);
    }

    @PostMapping("/posts/comments")
    public ResponseEntity<Comment> makeComment(@RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(commentService.createComment(commentDTO), HttpStatus.OK);
    }

    @GetMapping("/posts/comments/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable String id){
        return new ResponseEntity<>(commentService.getComment(id), HttpStatus.OK);
    }

    @PutMapping("/posts/comments/")
    public ResponseEntity<Comment> editComment(@RequestParam String message, @RequestParam String id){
        return new ResponseEntity<>(commentService.editComment(message, id), HttpStatus.OK);
    }

    @DeleteMapping("/posts/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") String id){
        return new ResponseEntity<String>(commentService.deleteComment(id), HttpStatus.OK);
    }

}
