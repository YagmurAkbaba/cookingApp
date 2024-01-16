package com.techcareer.graduationProject.cookingApp.dto.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeResponseDto {
    private Long likeId;
    private Long recipeId;
    private Long userId;
}
