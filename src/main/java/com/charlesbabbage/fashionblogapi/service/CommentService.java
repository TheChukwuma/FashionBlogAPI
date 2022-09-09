package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.dto.CommentDTO;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<APIResponse> createComment(CommentDTO commentDTO);

    ResponseEntity<APIResponse> getComment(Long id);

    ResponseEntity<APIResponse> editComment(CommentDTO commentDTO, Long id);

    ResponseEntity<APIResponse> deleteComment(Long id);

    ResponseEntity<APIResponse> searchComment(String keyword);
}
