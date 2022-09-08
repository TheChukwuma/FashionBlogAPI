package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.dto.CommentDTO;
import com.charlesbabbage.fashionblogapi.model.Comment;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.repository.CommentRepository;
import com.charlesbabbage.fashionblogapi.repository.PostRepository;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.service.CommentService;
import com.charlesbabbage.fashionblogapi.utils.ResponseUtil;
import com.charlesbabbage.fashionblogapi.utils.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    UserRepository userRepo;
    CommentRepository commentRepo;
    PostRepository postRepo;

    ResponseUtil responseUtil;

    @Override
    public ResponseEntity<APIResponse> createComment(CommentDTO commentDTO) {
        String id = UUID.getUniqueId();
        Post post = postRepo.findById(commentDTO.getPost_id()).get();
        User user = userRepo.findById(commentDTO.getUser_id()).get();
        Comment comment = new Comment();
        comment.setId(id);
        comment.setComment(commentDTO.getComment());
        comment.setPost(post);
        comment.setUser(user);
        return responseUtil.Okay(commentRepo.save(comment));
    }

    @Override
    public ResponseEntity<APIResponse> getComment(String id) {
        if (commentRepo.findById(id).isEmpty()){
            return responseUtil.NotFound("Comment bot found");
        }
        return responseUtil.Okay(commentRepo.findById(id).get());  }

    @Override
    public ResponseEntity<APIResponse> editComment(String message, String id) {
        if (commentRepo.findById(id).isEmpty()){
            return responseUtil.NotFound("Comment not found");
        }
        Comment comment = commentRepo.findById(id).get();
        comment.setComment(message);
        return responseUtil.Okay(commentRepo.save(comment));
    }

    @Override
    public ResponseEntity<APIResponse> deleteComment(String id) {
        if (commentRepo.findById(id).isEmpty()){
            return responseUtil.NotFound("Comment not found");
        }
        commentRepo.deleteById(id);
        return responseUtil.Deleted();
    }
}
