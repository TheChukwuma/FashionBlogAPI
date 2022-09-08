package com.charlesbabbage.fashionblogapi.repository;

import com.charlesbabbage.fashionblogapi.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, String> {
}
