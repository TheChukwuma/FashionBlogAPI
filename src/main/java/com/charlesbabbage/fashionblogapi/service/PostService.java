package com.charlesbabbage.fashionblogapi.service;

import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.model.Post;

public interface PostService {
    public Post uploadPost(PostDTO postDTO);


    String makeSlug(String title, String id);

    Post getPost(String id);
}
