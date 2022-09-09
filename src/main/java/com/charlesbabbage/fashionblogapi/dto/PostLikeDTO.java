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
    private String user_id;
    private String post_id;
    private String comment_id;





}
