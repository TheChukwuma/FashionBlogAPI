package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.model.Admin;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.repository.AdminRepository;
import com.charlesbabbage.fashionblogapi.repository.PostRepository;
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

    AdminRepository adminRepo;
    PostRepository postRepo;

    ResponseUtil responseUtil;

    @Override
    public ResponseEntity<APIResponse> uploadPost(PostDTO postDTO) {
        Post post = new Post();
        Admin admin = adminRepo.findById(postDTO.getAdmin_id()).get();
        post.setTitle(postDTO.getTitle());
        post.setImage(postDTO.getImage());
        post.setDescription(postDTO.getDescription());
        post.setSlug(makeSlug(post.getTitle(), postDTO.getAdmin_id()));
        post.setAdmin(admin);
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
    public ResponseEntity<APIResponse> deletePost(Long id, Long admin_id) {
        if (adminRepo.findById(admin_id).isEmpty()){
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
        List<Post> searchList = new ArrayList<>();
        searchList = postRepo.findAllByTitleContainingIgnoreCase(keyword);
        System.out.println(searchList);
        return responseUtil.Okay(searchList);
    }
}