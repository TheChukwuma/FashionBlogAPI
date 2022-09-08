package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import org.springframework.http.ResponseEntity;

public interface PostService {
    public ResponseEntity<APIResponse> uploadPost(PostDTO postDTO);


    String makeSlug(String title, String id);

    ResponseEntity<APIResponse> getPost(String id);

    ResponseEntity<APIResponse> deletePost(String id);
}
