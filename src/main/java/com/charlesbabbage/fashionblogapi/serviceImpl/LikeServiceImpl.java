package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.model.Comment;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.model.Like;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.repository.CommentRepository;
import com.charlesbabbage.fashionblogapi.repository.LikeRepository;
import com.charlesbabbage.fashionblogapi.repository.PostRepository;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.service.LikeService;
import com.charlesbabbage.fashionblogapi.utils.ResponseUtil;
import com.charlesbabbage.fashionblogapi.utils.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    UserRepository userRepo;
    PostRepository postRepo;
    LikeRepository likeRepo;
    CommentRepository commentRepo;
    ResponseUtil responseUtil;


    @Override
    public ResponseEntity<APIResponse> likeAPost(Long user_id, Long post_id) {
        Like like = new Like();
        User user = userRepo.findById(user_id).get();
        if (postRepo.findById(post_id).isEmpty()){
            return responseUtil.NotFound("Post Not Found!");
        }
        Post post = postRepo.findById(post_id).get();
        like.setPost(post);
        like.setUser(user);
        if (getLikeOfPost(user_id, post_id) == null){
            like.setIsLiked(true);

            return responseUtil.Okay(likeRepo.save(like));
        }else {
            return unlikePost(user_id, post_id );
        }
    }

    @Override
    public ResponseEntity<APIResponse> likeAComment(Long user_id, Long comment_id) {
        Like commentLike = new Like();
        User user = userRepo.findById(user_id).get();
        if (commentRepo.findById(comment_id).isEmpty()){
            return responseUtil.NotFound("Comment Not Found!");
        }
        Comment comment = commentRepo.findById(comment_id).get();
        commentLike.setComment(comment);
        commentLike.setUser(user);
        if (getLikeOfComment(user_id,comment_id) == null){
            commentLike.setIsLiked(true);

            return responseUtil.Okay(likeRepo.save(commentLike));
        }else {
            return unlikeComment(user_id, comment_id );
        }
    }

    @Override
    public ResponseEntity<APIResponse> unlikePost(Long user_id, Long post_id){
        likeRepo.deleteByUserIdAndPostId(user_id, post_id);
        return responseUtil.AlreadyLiked();
    }
    @Override
    public ResponseEntity<APIResponse> unlikeComment(Long user_id, Long comment_id){

        likeRepo.deleteByUserIdAndCommentId(user_id, comment_id);
        return responseUtil.AlreadyLiked();
    }

    @Override
    public Like getLikeOfPost(Long user_id, Long post_id){
        return likeRepo.getByUserIdAndPostId(user_id, post_id);
    }

    @Override
    public Like getLikeOfComment(Long user_id, Long comment_id){
        return likeRepo.getByUserIdAndCommentId(user_id, comment_id);
    }

}
