package com.charlesbabbage.fashionblogapi.dto;

import lombok.Data;

@Data
public class PostDTO {
    private String title;
    private String description;
    private String image;
    private String user_id;

}
