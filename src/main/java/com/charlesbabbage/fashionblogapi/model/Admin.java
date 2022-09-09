package com.charlesbabbage.fashionblogapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "admin",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "admin",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "admin",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

}
