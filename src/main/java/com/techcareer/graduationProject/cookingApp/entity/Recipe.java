package com.techcareer.graduationProject.cookingApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "recipe")
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;

}
