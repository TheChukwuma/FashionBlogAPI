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
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    UserRepository userRepo;
    CommentRepository commentRepo;
    PostRepository postRepo;

    ResponseUtil responseUtil;

    @Override
    public ResponseEntity<APIResponse> createComment(CommentDTO commentDTO) {
        if(postRepo.findById(commentDTO.getPost_id()).isEmpty()){
            return responseUtil.NotFound("POST, NOT FOUND!");
        }
        Post post = postRepo.findById(commentDTO.getPost_id()).get();
        User user = userRepo.findById(commentDTO.getUser_id()).get();
//        if (!Objects.equals(post.getUser().getId(),user.getId())){
//            throw new RuntimeException();
//        }
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setPost(post);
        comment.setUser(user);
        return responseUtil.Okay(commentRepo.save(comment));
    }

    @Override
    public ResponseEntity<APIResponse> getComment(Long id) {
        if (commentRepo.findById(id).isEmpty()){
            return responseUtil.NotFound("Comment not found");
        }
        return responseUtil.Okay(commentRepo.findById(id).get());  }

    @Override
    public ResponseEntity<APIResponse> editComment(CommentDTO commentDTO, Long id) {
        if (commentRepo.findById(id).isEmpty()){
            return responseUtil.NotFound("Comment not found");
        }
        Comment comment = commentRepo.findById(id).get();
        comment.setComment(commentDTO.getComment());
        return responseUtil.Okay(commentRepo.save(comment));
    }

    @Override
    public ResponseEntity<APIResponse> deleteComment(Long id) {
        if (commentRepo.findById(id).isEmpty()){
            return responseUtil.NotFound("Comment not found");
        }
        commentRepo.deleteById(id);
        return responseUtil.Deleted();
    }

    @Override
    public ResponseEntity<APIResponse> searchComment(String keyword) {
        if (commentRepo.findAllByCommentContainingIgnoreCase(keyword).isEmpty()){
            return responseUtil.Okay("NO POST FOUND");
        }
        List<Comment> searchList = commentRepo.findAllByCommentContainingIgnoreCase(keyword);

        return responseUtil.Okay(searchList);
    }

}
