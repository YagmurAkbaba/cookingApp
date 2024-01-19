package com.techcareer.graduationProject.cookingApp.dto.responseDto;

import com.techcareer.graduationProject.cookingApp.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeResponseDto {
   // private User user; Loaded lazyly if it was Eagerly then it could be
    private String title;
    private List<String> ingredients;
    private String text;
    private String userName;
    private Long userId;
}
