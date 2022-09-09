package com.charlesbabbage.fashionblogapi.controller;

import com.charlesbabbage.fashionblogapi.dto.CommentDTO;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.service.CommentService;
import com.charlesbabbage.fashionblogapi.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class CommentController {

    private final CommentService commentService;

    private final LikeService likeService;

    @PostMapping("/posts/comments")
    public ResponseEntity<APIResponse> makeComment(@RequestBody CommentDTO commentDTO){
        return commentService.createComment(commentDTO);
    }

    @GetMapping("/posts/comments/{id}")
    public ResponseEntity<APIResponse> getComment(@PathVariable Long id){
        return commentService.getComment(id);
    }

    @PutMapping("/posts/comments/{id}")
    public ResponseEntity<APIResponse> editComment(@RequestBody CommentDTO commentDTO, @PathVariable Long id){
        return commentService.editComment(commentDTO, id);
    }

    @DeleteMapping("/posts/comments/{id}")
    public ResponseEntity<APIResponse> deleteComment(@PathVariable("id") Long id){
        return commentService.deleteComment(id);
    }

    @PostMapping("/posts/comments/likes/{user_id}/{id}")
    public ResponseEntity<APIResponse> likeComment(@PathVariable("user_id") Long user_id, @PathVariable("id") Long comment_id){
        return likeService.likeAComment(user_id, comment_id);
    }

    @GetMapping("/comments/search/{keyword}")
    public ResponseEntity<APIResponse> searchComment(@PathVariable String keyword){
        return commentService.searchComment(keyword);
    }

}
