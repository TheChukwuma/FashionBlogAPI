package com.charlesbabbage.fashionblogapi.serviceImpl;

import com.charlesbabbage.fashionblogapi.dto.AdminDTO;
import com.charlesbabbage.fashionblogapi.enums.LoginEnum;
import com.charlesbabbage.fashionblogapi.model.Admin;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.repository.AdminRepository;
import com.charlesbabbage.fashionblogapi.repository.PostRepository;
import com.charlesbabbage.fashionblogapi.service.AdminService;
import com.charlesbabbage.fashionblogapi.utils.HashPassword;
import com.charlesbabbage.fashionblogapi.utils.ResponseUtil;
import com.charlesbabbage.fashionblogapi.utils.ValidatePassword;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepo;

    private final PostRepository postRepo;

    private final ResponseUtil responseUtil;

    @Override
    public ResponseEntity<APIResponse> register(AdminDTO adminDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Admin existingEmail = adminRepo.findByEmail(adminDTO.getEmail());
         if (existingEmail != null) {
            return responseUtil.AlreadyExist(String.format("%s already exist", adminDTO.getEmail()));
        }
        else {
        Admin admin = new Admin();
        String hashedPassword = HashPassword.generateStrongPasswordHash(adminDTO.getPassword());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(hashedPassword);
        return responseUtil.Okay(adminRepo.save(admin));
        }
    }

    @Override
    public ResponseEntity<APIResponse> login(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Admin admin = adminRepo.findByEmail(email);
        if (admin != null){
            if (ValidatePassword.validatePassword(password, admin.getPassword())){
                return responseUtil.Okay(LoginEnum.SUCCESS);
            }
        }
        return responseUtil.NotFound(String.format("Incorrect user login combinations"));
    }

    @Override
    public ResponseEntity<APIResponse> getAdmin(Long admin_id) {
        if(adminRepo.findById(admin_id).isEmpty()){
            return responseUtil.NotFound("ADMIN NOT FOUND");
        }return responseUtil.Okay(adminRepo.findById(admin_id).get());
    }


}
