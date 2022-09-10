package com.charlesbabbage.fashionblogapi.controller;

import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.service.LikeService;
import com.charlesbabbage.fashionblogapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class PostController {

    private final PostService postService;

    private final LikeService likeService;


    @PostMapping("/posts")
    public ResponseEntity<APIResponse> createPost(@RequestBody PostDTO postDTO){
        return postService.uploadPost(postDTO);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<APIResponse> getPost(@PathVariable("id") Long id){
        return postService.getPost(id);
    }

    @DeleteMapping("/posts/{user_id}/{id}")
    public ResponseEntity<APIResponse> deletePost(@PathVariable("user_id") Long user_id,@PathVariable("id") Long id){
        return postService.deletePost(user_id, id);
    }


    @PostMapping("/posts/likes/{user_id}/{id}")
    public ResponseEntity<APIResponse> likePost(@PathVariable("user_id") Long user_id, @PathVariable("id") Long post_id){
        return likeService.likeAPost(user_id, post_id);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<APIResponse> searchPost(@PathVariable String keyword){
        return postService.searchPost(keyword);
    }
}
