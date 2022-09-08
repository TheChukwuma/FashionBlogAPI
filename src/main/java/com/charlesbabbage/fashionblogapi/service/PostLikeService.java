package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.dto.PostLikeDTO;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import org.springframework.http.ResponseEntity;

public interface PostLikeService {

    ResponseEntity<APIResponse> likeAPost(PostLikeDTO postLikeDTO);

    ResponseEntity<APIResponse> unlikeAPost(PostLikeDTO postLikeDTO);

    ResponseEntity<APIResponse> likeAComment(PostLikeDTO postLikeDTO);

    ResponseEntity<APIResponse> unlikeAComment(PostLikeDTO postLikeDTO);
}
