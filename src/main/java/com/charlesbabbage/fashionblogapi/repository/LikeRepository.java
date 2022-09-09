package com.charlesbabbage.fashionblogapi.repository;

import com.charlesbabbage.fashionblogapi.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, String> {

    void deleteByUserIdAndCommentId(String user_id, String comment_id);

    Like getByUserIdAndPostId(String user_id, String post_id);

    Like getByUserIdAndCommentId(String user_id, String comment_id);

    void deleteByUserIdAndPostId(String user_id, String post_id);

    String getIdByUserIdAndPostId(String user_id, String post_id);
}
