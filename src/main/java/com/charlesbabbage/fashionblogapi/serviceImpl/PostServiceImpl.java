package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.enums.UserRole;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.repository.PostRepository;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.service.PostService;
import com.charlesbabbage.fashionblogapi.utils.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    UserRepository userRepo;
    PostRepository postRepo;

    ResponseUtil responseUtil;

    @Override
    public ResponseEntity<APIResponse> uploadPost(PostDTO postDTO) {

        Post post = new Post();
        User user = userRepo.findById(postDTO.getUser_id()).get();
        if (user.getRole().equals(UserRole.USER.name())){
            return responseUtil.NotAnAdmin();
        }
        post.setTitle(postDTO.getTitle());
        post.setImage(postDTO.getImage());
        post.setDescription(postDTO.getDescription());
        post.setSlug(makeSlug(post.getTitle(), postDTO.getUser_id()));
        post.setUser(user);
        return responseUtil.Okay(postRepo.save(post));
    }

    @Override
    public String makeSlug(String title, Long id) {
        String slug = title.replaceAll("\\s", "-");
        return (slug + id).toLowerCase(Locale.ENGLISH);
    }

    @Override
    public ResponseEntity<APIResponse> getPost(Long id) {
        if (postRepo.findById(id).isEmpty()) {
            return responseUtil.NotFound("Post does not exist");
        } else {
            return responseUtil.Okay(postRepo.findById(id).get());
        }
    }

    @Override
    public ResponseEntity<APIResponse> deletePost(Long user_id, Long id) {
        if (userRepo.findById(user_id).isEmpty()){
            return responseUtil.NotFound("ADMIN, NOT FOUND!");
        }
        User user = userRepo.findById(user_id).get();
        if (user.getRole().equals(UserRole.USER.name())){
            return responseUtil.NotAnAdmin();
        }
        if (postRepo.findById(id).isEmpty()) {
            return responseUtil.NotFound("Post does not exist");
        } else {
            postRepo.deleteById(id);
            return responseUtil.Deleted();
        }
    }

    @Override
    public ResponseEntity<APIResponse> searchPost(String keyword) {
        if (postRepo.findAllByTitleContainingIgnoreCase(keyword).isEmpty()){
            return responseUtil.Okay("NO POST FOUND");
        }
        List<Post> searchList = postRepo.findAllByTitleContainingIgnoreCase(keyword);

        System.out.println(searchList);
        return responseUtil.Okay(searchList);
    }
}