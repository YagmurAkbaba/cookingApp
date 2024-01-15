package com.techcareer.graduationProject.cookingApp.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeCreateRequestDto {
    private String title;
    private List<String> ingredients;
    private String text;
    private Long userId;
}
