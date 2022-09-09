package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.model.Like;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import org.springframework.http.ResponseEntity;

public interface LikeService {

    ResponseEntity<APIResponse> likeAPost(Long user_id, Long post_id);

    ResponseEntity<APIResponse> likeAComment(Long user_id, Long comment_id);

    ResponseEntity<APIResponse> unlikePost(Long user_id, Long post_id);

    ResponseEntity<APIResponse> unlikeComment(Long user_id, Long comment_id);

    Like getLikeOfPost(Long user_id, Long post_id);

    Like getLikeOfComment(Long user_id, Long comment_id);
}
