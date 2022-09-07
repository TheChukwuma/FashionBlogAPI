package com.charlesbabbage.fashionblogapi.repository;

import com.charlesbabbage.fashionblogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
