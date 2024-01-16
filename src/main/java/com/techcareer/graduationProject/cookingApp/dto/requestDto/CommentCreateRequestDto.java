package com.techcareer.graduationProject.cookingApp.dto.requestDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.Mapping;

@Getter
@Setter
public class CommentCreateRequestDto {
    private Long recipeId;
    private Long userId;
    private String text;

}
