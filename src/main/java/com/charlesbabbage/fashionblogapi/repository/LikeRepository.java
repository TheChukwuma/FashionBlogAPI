package com.charlesbabbage.fashionblogapi.repository;

import com.charlesbabbage.fashionblogapi.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface LikeRepository extends JpaRepository<Like, Long> {

    void deleteByUserIdAndCommentId(Long user_id, Long comment_id);

    Like getByUserIdAndPostId(Long user_id, Long post_id);

    Like getByUserIdAndCommentId(Long user_id, Long comment_id);

    void deleteByUserIdAndPostId(Long user_id, Long post_id);

}
