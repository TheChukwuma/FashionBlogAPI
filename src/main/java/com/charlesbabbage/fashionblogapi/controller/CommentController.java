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

    @PostMapping("/posts/comments/likes/{user_id}/{id}")
    public ResponseEntity<APIResponse> likeComment(@PathVariable("user_id") String user_id, @PathVariable("id") String comment_id){
        return likeService.likeAComment(user_id, comment_id);
    }

    @GetMapping("/comments/search/{keyword}")
    public ResponseEntity<APIResponse> searchComment(@PathVariable String keyword){
        return commentService.searchComment(keyword);
    }

}
