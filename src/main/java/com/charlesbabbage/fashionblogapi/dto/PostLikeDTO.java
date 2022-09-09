package com.charlesbabbage.fashionblogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostLikeDTO {

    private Boolean isLiked = false;
    private Long user_id;
    private Long post_id;
    private Long comment_id;





}
