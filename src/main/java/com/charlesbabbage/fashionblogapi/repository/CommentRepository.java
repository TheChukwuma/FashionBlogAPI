package com.charlesbabbage.fashionblogapi.repository;


import com.charlesbabbage.fashionblogapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {
}
