package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import org.springframework.http.ResponseEntity;

public interface PostService {
    public ResponseEntity<APIResponse> uploadPost(PostDTO postDTO);


    String makeSlug(String title, Long id);

    ResponseEntity<APIResponse> getPost(Long id);

    ResponseEntity<APIResponse> deletePost(Long user_id, Long id);

    ResponseEntity<APIResponse> searchPost(String keyword);
}
