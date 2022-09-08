package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.dto.CommentDTO;
import com.charlesbabbage.fashionblogapi.exception.ResourceNotFoundException;
import com.charlesbabbage.fashionblogapi.model.Comment;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.repository.CommentRepository;
import com.charlesbabbage.fashionblogapi.repository.PostRepository;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.service.CommentService;
import com.charlesbabbage.fashionblogapi.utils.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    UserRepository userRepo;
    CommentRepository commentRepo;
    PostRepository postRepo;

    @Override
    public Comment createComment(CommentDTO commentDTO) {
        String id = UUID.getUniqueId();
        Post post = postRepo.findById(commentDTO.getPost_id()).get();
        User user = userRepo.findById(commentDTO.getUser_id()).get();
        Comment comment = new Comment();
        comment.setId(id);
        comment.setComment(commentDTO.getComment());
        comment.setPost(post);
        comment.setUser(user);
        return commentRepo.save(comment);
    }

    @Override
    public Comment getComment(String id) {
        return commentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Comment","id",id));
    }

    @Override
    public Comment editComment(String message, String id) {
        Comment comment = getComment(id);
        comment.setComment(message);
        return commentRepo.save(comment);
    }

    @Override
    public String deleteComment(String id) {
        getComment(id);
        commentRepo.deleteById(id);
        return "deleted";
    }
}
