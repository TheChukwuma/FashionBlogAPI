package com.charlesbabbage.fashionblogapi.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private String id;
    private String comment;
    private String post_id;
    private String user_id;
}
