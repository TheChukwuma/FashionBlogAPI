package com.charlesbabbage.fashionblogapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Like {

    private Long id;

    private Boolean isLiked;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;
}
