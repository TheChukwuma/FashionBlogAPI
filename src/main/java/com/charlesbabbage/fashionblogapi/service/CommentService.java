package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.dto.CommentDTO;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<APIResponse> createComment(CommentDTO commentDTO);

    ResponseEntity<APIResponse> getComment(String id);

    ResponseEntity<APIResponse> editComment(String message, String id);

    ResponseEntity<APIResponse> deleteComment(String id);
}
