package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.dto.CommentDTO;
import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.enums.LoginEnum;
import com.charlesbabbage.fashionblogapi.exception.ApiResponseException;
import com.charlesbabbage.fashionblogapi.model.Comment;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.repository.PostRepository;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.service.UserService;
import com.charlesbabbage.fashionblogapi.utils.HashPassword;
import com.charlesbabbage.fashionblogapi.utils.UUID;
import com.charlesbabbage.fashionblogapi.utils.ValidatePassword;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepo;

    PostRepository postRepo;

    @Override
    public User register(UserDTO userDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User existingUsername = userRepo.findByUsername(userDTO.getUsername());
        User existingEmail = userRepo.findByEmail(userDTO.getEmail());
        if (existingUsername != null ){
            throw new ApiResponseException(HttpStatus.CONFLICT, String.format("%s already has an account", userDTO.getUsername()));
        }else if (existingEmail != null) {
            throw new ApiResponseException(HttpStatus.CONFLICT, String.format("%s already has an account", userDTO.getEmail()));
        }
        else {
        User user = new User();
        String hashedPassword = HashPassword.generateStrongPasswordHash(userDTO.getPassword());
        user.setId( UUID.getUniqueId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername().toLowerCase());
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setPassword(hashedPassword);
        return userRepo.save(user);
        }
    }

    @Override
    public String login(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = userRepo.findByUsername(username.toLowerCase());
        if (user != null){
            if (ValidatePassword.validatePassword(password, user.getPassword())){
                return LoginEnum.SUCCESS.name();
            }
        }
        throw new ApiResponseException(HttpStatus.NOT_FOUND, "Incorrect username or password");
    }



}
