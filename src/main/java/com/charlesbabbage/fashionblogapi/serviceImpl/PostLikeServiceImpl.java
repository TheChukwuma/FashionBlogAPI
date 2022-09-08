package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.dto.PostLikeDTO;
import com.charlesbabbage.fashionblogapi.model.Comment;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.model.PostLike;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.repository.CommentRepository;
import com.charlesbabbage.fashionblogapi.repository.PostLikeRepository;
import com.charlesbabbage.fashionblogapi.repository.PostRepository;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.service.PostLikeService;
import com.charlesbabbage.fashionblogapi.utils.ResponseUtil;
import com.charlesbabbage.fashionblogapi.utils.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

    UserRepository userRepo;
    PostRepository postRepo;
    PostLikeRepository likeRepo;
    CommentRepository commentRepo;
    ResponseUtil responseUtil;





    @Override
    public ResponseEntity<APIResponse> likeAPost(PostLikeDTO postLikeDTO) {
        PostLike postLike = new PostLike();
        User user = userRepo.findById(postLikeDTO.getUser_id()).get();
        if (postRepo.findById(postLikeDTO.getPost_id()).isEmpty()){
            return responseUtil.NotFound("Post Not Found!");
        }
        Post post = postRepo.findById(postLikeDTO.getPost_id()).get();
        String id = UUID.getUniqueId();
        postLike.setIsLiked(true);
        postLike.setPost(post);
        postLike.setUser(user);
        postLike.setId(id);
        return responseUtil.Okay(likeRepo.save(postLike));

    }

    @Override
    public ResponseEntity<APIResponse> unlikeAPost(PostLikeDTO likeDTO) {
        PostLike postUnLike = new PostLike();
        User user = userRepo.findById(likeDTO.getUser_id()).get();
        if (postRepo.findById(likeDTO.getPost_id()).isEmpty()){
            return responseUtil.NotFound("Post Not Found!");
        }
        if (likeDTO.getIsLiked() == true) {
            Post post = postRepo.findById(likeDTO.getPost_id()).get();
            String id = UUID.getUniqueId();
            postUnLike.setIsLiked(false);
            postUnLike.setPost(post);
            postUnLike.setUser(user);
            postUnLike.setId(id);
            return responseUtil.Okay(likeRepo.save(postUnLike));
        }
        return responseUtil.AlreadyLiked();
    }

    @Override
    public ResponseEntity<APIResponse> likeAComment(PostLikeDTO postLikeDTO) {
        PostLike commentLike = new PostLike();
        User user = userRepo.findById(postLikeDTO.getUser_id()).get();
        if (commentRepo.findById(postLikeDTO.getComment_id()).isEmpty()){
            return responseUtil.NotFound("Comment Not Found!");
        }
        Comment comment = commentRepo.findById(postLikeDTO.getComment_id()).get();
        String id = UUID.getUniqueId();
        commentLike.setIsLiked(true);
        commentLike.setComment(comment);
        commentLike.setUser(user);
        commentLike.setId(id);
        return responseUtil.Okay(likeRepo.save(commentLike));
    }

    @Override
    public ResponseEntity<APIResponse> unlikeAComment(PostLikeDTO postLikeDTO) {
        PostLike commentUnLike = new PostLike();
        User user = userRepo.findById(postLikeDTO.getUser_id()).get();
        if (commentRepo.findById(postLikeDTO.getComment_id()).isEmpty()){
            return responseUtil.NotFound("Post Not Found!");
        }
        if (postLikeDTO.getIsLiked() == true) {
            Comment comment = commentRepo.findById(postLikeDTO.getPost_id()).get();
            String id = UUID.getUniqueId();
            commentUnLike.setIsLiked(false);
            commentUnLike.setComment(comment);
            commentUnLike.setUser(user);
            commentUnLike.setId(id);
            return responseUtil.Okay(likeRepo.save(commentUnLike));
        }
        return responseUtil.AlreadyLiked();
    }
}
