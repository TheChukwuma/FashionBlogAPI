package com.charlesbabbage.fashionblogapi.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String comment;
    private Long post_id;
    private Long user_id;
}
