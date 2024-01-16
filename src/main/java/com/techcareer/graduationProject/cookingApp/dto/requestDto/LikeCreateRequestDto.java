package com.techcareer.graduationProject.cookingApp.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeCreateRequestDto {
    private Long recipeId;
    private Long userId;
}
