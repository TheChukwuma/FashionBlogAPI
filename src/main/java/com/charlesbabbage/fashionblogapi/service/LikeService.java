package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.model.Like;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import org.springframework.http.ResponseEntity;

public interface LikeService {

    ResponseEntity<APIResponse> likeAPost(String user_id, String post_id);

    ResponseEntity<APIResponse> likeAComment(String user_id, String comment_id);

    ResponseEntity<APIResponse> unlikePost(String user_id, String post_id);

    ResponseEntity<APIResponse> unlikeComment(String user_id, String comment_id);

    Like getLikeOfPost(String user_id, String post_id);

    Like getLikeOfComment(String user_id, String comment_id);
}
