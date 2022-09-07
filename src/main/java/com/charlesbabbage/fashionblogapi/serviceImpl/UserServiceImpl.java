package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.dto.CommentDTO;
import com.charlesbabbage.fashionblogapi.dto.PostDTO;
import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.enums.LoginEnum;
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
        if (existingUsername != null || existingEmail != null ){
            return new User();
        } else {
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
        return LoginEnum.FAILED.name();
    }

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
        return postRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
    }

    @Override
    public Comment makeComment(CommentDTO commentDTO){
        return null;
    }
}
