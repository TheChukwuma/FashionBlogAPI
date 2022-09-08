package com.charlesbabbage.fashionblogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PostLikeDTO {
    private Boolean isLiked;
    private String user_id;
    private String post_id;
    private String comment_id;


}
