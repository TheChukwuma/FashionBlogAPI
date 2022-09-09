package com.charlesbabbage.fashionblogapi.repository;

import com.charlesbabbage.fashionblogapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

//    @Query(value = "SELECT * FROM post p WHERE p.title LIKE ? ", nativeQuery = true )
//    List<Post> findAllByTitle(String keyword);

    List<Post> findAllByTitleContainingIgnoreCase(String keyword);

}
