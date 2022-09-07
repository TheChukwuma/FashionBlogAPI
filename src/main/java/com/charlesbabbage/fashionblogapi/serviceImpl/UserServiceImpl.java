package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.enums.LoginEnum;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.service.UserService;
import com.charlesbabbage.fashionblogapi.utils.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepo;

    @Override
    public User register(UserDTO userDTO) {
        User user = new User();
        user.setId( UUID.getUniqueId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername().toLowerCase());
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setPassword(userDTO.getPassword());
        return userRepo.save(user);
    }

    @Override
    public String login(String username, String password) {
        User user = userRepo.findByUsername(username.toLowerCase());
        if (user != null){
            if (user.getPassword().equals(password)){
                return LoginEnum.SUCCESS.name();
            }
        }
        return LoginEnum.FAILED.name();
    }
}
