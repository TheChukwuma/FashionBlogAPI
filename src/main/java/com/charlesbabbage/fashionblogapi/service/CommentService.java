package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.dto.CommentDTO;
import com.charlesbabbage.fashionblogapi.model.Comment;

public interface CommentService {

    Comment createComment(CommentDTO commentDTO);

    Comment getComment(String id);

    Comment editComment(String message, String id);

    String deleteComment(String id);
}
