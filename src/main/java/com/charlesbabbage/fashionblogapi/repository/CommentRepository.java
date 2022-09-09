package com.charlesbabbage.fashionblogapi.repository;


import com.charlesbabbage.fashionblogapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//    @Query(value = "SELECT * FROM comment c WHERE c.comment LIKE '%?%'", nativeQuery = true)
//    List<Comment> findAllByComment(String keyword);

    List<Comment> findAllByCommentContainingIgnoreCase(String keyword);
}
