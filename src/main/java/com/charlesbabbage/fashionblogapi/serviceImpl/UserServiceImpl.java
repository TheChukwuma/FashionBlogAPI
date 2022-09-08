package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.enums.LoginEnum;
import com.charlesbabbage.fashionblogapi.exception.ApiResponseException;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.repository.PostRepository;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.service.UserService;
import com.charlesbabbage.fashionblogapi.utils.HashPassword;
import com.charlesbabbage.fashionblogapi.utils.ResponseUtil;
import com.charlesbabbage.fashionblogapi.utils.UUID;
import com.charlesbabbage.fashionblogapi.utils.ValidatePassword;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final PostRepository postRepo;

    private final ResponseUtil responseUtil;

    @Override
    public ResponseEntity<APIResponse> register(UserDTO userDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User existingUsername = userRepo.findByUsername(userDTO.getUsername());
        User existingEmail = userRepo.findByEmail(userDTO.getEmail());
        if (existingUsername != null ){
            return responseUtil.AlreadyExist(String.format("%s already exist",userDTO.getUsername()));
        }else if (existingEmail != null) {
            return responseUtil.AlreadyExist(String.format("%s already exist",userDTO.getEmail()));
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
        return responseUtil.Okay(userRepo.save(user));
        }
    }

    @Override
    public ResponseEntity<APIResponse> login(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = userRepo.findByUsername(username.toLowerCase());
        if (user != null){
            if (ValidatePassword.validatePassword(password, user.getPassword())){
                return responseUtil.Okay(LoginEnum.SUCCESS);
            }
        }
        return responseUtil.NotFound(String.format("Incorrect user login combinations"));
    }



}
