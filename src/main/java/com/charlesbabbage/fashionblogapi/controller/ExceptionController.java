//package com.charlesbabbage.fashionblogapi.controller;
//
//
//import com.charlesbabbage.fashionblogapi.exception.UserNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import javax.naming.AuthenticationException;
//
//@ControllerAdvice
//public class ExceptionController {
//    // For REST APIs
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<?> UserNotFoundException(UserNotFoundException ex) {
//        return new ResponseEntity<>(new APIResponse(ex.getMessage(), true), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<?> AuthenticationException(AuthenticationException ex) {
//        return new ResponseEntity<>(new APIResponse(ex.getMessage(), true), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(PostNotFoundException.class)
//    public ResponseEntity<?> PostNotFoundException(PostNotFoundException ex) {
//        return new ResponseEntity<>(new APIResponse(ex.getMessage(), true), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(LikeNotFoundException.class)
//    public ResponseEntity<?> LikeNotFoundException(LikeNotFoundException ex) {
//        return new ResponseEntity<>(new APIResponse(ex.getMessage(), true), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(CommentNotFoundException.class)
//    public ResponseEntity<?> CommentNotFoundException(CommentNotFoundException ex) {
//        return new ResponseEntity<>(new APIResponse(ex.getMessage(), true), HttpStatus.NOT_FOUND);
//    }
//
//}
