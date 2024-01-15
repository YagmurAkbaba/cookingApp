package com.techcareer.graduationProject.cookingApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // private Long recipeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // Bir user silindiğinde bütün tarifleri da silinsin
    @JsonIgnore
    Recipe recipe;

    // private Long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // Bir user silindiğinde bütün tarifleri da silinsin
    @JsonIgnore
    User user;

    @Lob
    @Column(columnDefinition = "text")
    private String text;

}
