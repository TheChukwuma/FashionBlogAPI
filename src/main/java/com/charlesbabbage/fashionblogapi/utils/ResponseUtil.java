package com.charlesbabbage.fashionblogapi.utils;

import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ResponseUtil<T> {


    public ResponseEntity<APIResponse> Okay(T response){
        return  new  ResponseEntity<>(new APIResponse<>("Request Successful", true, response), HttpStatus.OK);
    }

    public ResponseEntity<APIResponse> NotFound(String message){
        return  new ResponseEntity<>(new APIResponse<>(message, true, null), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<APIResponse> AlreadyExist(String message){
        return  new ResponseEntity<>(new APIResponse<>(message, true, null), HttpStatus.CONFLICT);
    }

    public ResponseEntity<APIResponse> Deleted(){
        return new ResponseEntity<>(new APIResponse<>("DELETED", true, null), HttpStatus.OK);
    }

    public ResponseEntity<APIResponse> NotDeleted(){
        return new ResponseEntity<>(new APIResponse<>("NOT DELETED", true, null),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<APIResponse> AlreadyLiked(){
        return new ResponseEntity<>(new APIResponse<>("ALREADY LIKED, SO, NOW UNLIKED", true, null), HttpStatus.CONFLICT);
    }

    public ResponseEntity<APIResponse> NotAnAdmin(){
        return new ResponseEntity<>(new APIResponse<>("CANNOT CREATE OR DELETE A POST, NOT AN ADMIN", true, null), HttpStatus.BAD_REQUEST);
    }

}
