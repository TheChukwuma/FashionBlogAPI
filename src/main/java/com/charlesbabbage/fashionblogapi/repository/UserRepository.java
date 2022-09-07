package com.charlesbabbage.fashionblogapi.repository;

import com.charlesbabbage.fashionblogapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

    User findByEmail(String email);


}
