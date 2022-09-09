package com.charlesbabbage.fashionblogapi;

import com.charlesbabbage.fashionblogapi.dto.UserDTO;
import com.charlesbabbage.fashionblogapi.model.Comment;
import com.charlesbabbage.fashionblogapi.model.Like;
import com.charlesbabbage.fashionblogapi.model.Post;
import com.charlesbabbage.fashionblogapi.model.User;
import com.charlesbabbage.fashionblogapi.pojos.APIResponse;
import com.charlesbabbage.fashionblogapi.repository.CommentRepository;
import com.charlesbabbage.fashionblogapi.repository.LikeRepository;
import com.charlesbabbage.fashionblogapi.repository.PostRepository;
import com.charlesbabbage.fashionblogapi.repository.UserRepository;
import com.charlesbabbage.fashionblogapi.serviceImpl.UserServiceImpl;
import com.charlesbabbage.fashionblogapi.utils.ResponseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.Month.AUGUST;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Mock
    private LikeRepository likeRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private PostRepository postRepo;

    @Mock
    private CommentRepository commentRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private Like like;
    private Post post;
    private Comment comment;
    private LocalDateTime localDateTime;
    List<User> users = new ArrayList<>();
    List<Post> posts = new ArrayList<>();
    List<Comment> comments = new ArrayList<>();

    List<Like> likes = new ArrayList<>();

    ResponseUtil<APIResponse> responseUtil;

    ResponseEntity<APIResponse> response;

//    @BeforeEach
//    void setUp(){
//        localDateTime = LocalDateTime.of(2022, AUGUST,3,6,30,40,50000);
//        user = new User(2L, "Sizwe", "Bansi", "bansi", "sbansi@gmail.com","1234", localDateTime, localDateTime,posts, likes, comments );
//        post = new Post(4L, "Title", "Description", "Image String", "slug",localDateTime,localDateTime,user,comments,likes);
//        like = new Like(4L, false, localDateTime, localDateTime, user, post, comment);
//        comment = new Comment(4L, "my comment", localDateTime,localDateTime, post, likes, user);
//
//    }
//
//    @Test
//    void registerTest() throws NoSuchAlgorithmException, InvalidKeySpecException {
//        UserDTO userDTO = new UserDTO("bansi","1234");
//        when(userRepo.save(user)).thenReturn(user);
//
//    }

}
