package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.exception.ResourceNotFoundException;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.repository.PostRepository;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.service.PostService;
import com.charlesbabbage.fashionblogapi.utils.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    UserRepository userRepo;
    PostRepository postRepo;

    @Override
    public Post uploadPost(PostDTO postDTO) {
        Post post = new Post();
        User user = userRepo.findById(postDTO.getUser_id()).get();
        String id = UUID.getUniqueId();
        post.setId(id);
        post.setTitle(postDTO.getTitle());
        post.setImage(postDTO.getImage());
        post.setDescription(postDTO.getDescription());
        post.setSlug(makeSlug(post.getTitle(),id));
        post.setUser(user);
        return postRepo.save(post);
    }

    @Override
    public String makeSlug(String title, String id) {
        String slug = title.replaceAll("\\s","-");
        return (slug + id.substring(0,8)).toLowerCase(Locale.ENGLISH);
    }

    @Override
    public Post getPost(String id){
        return postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id", id));
    }
}
