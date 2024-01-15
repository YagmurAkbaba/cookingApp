package com.techcareer.graduationProject.cookingApp.service;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.RecipeCreateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.requestDto.RecipeUpdateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.RecipeResponseDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.UserResponseDto;
import com.techcareer.graduationProject.cookingApp.entity.Recipe;
import com.techcareer.graduationProject.cookingApp.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public List<RecipeResponseDto> getAllRecipes(Optional<Long> userId) {
        if (userId.isPresent()) {
            return modelMapper.map(recipeRepository.findByUser_UserId(userId.get()), new TypeToken<List<RecipeResponseDto>>() {
            }.getType());
        }

        return modelMapper.map(recipeRepository.findAll(), new TypeToken<List<RecipeResponseDto>>() {}.getType());
    }

    public RecipeResponseDto getRecipeById(Long recipeId) {
        return modelMapper.map(recipeRepository.findById(recipeId), RecipeResponseDto.class);
    }

    public Boolean createNewRecipe(RecipeCreateRequestDto recipeCreateRequestDto) {
        UserResponseDto userResponseDto = userService.getUserById(recipeCreateRequestDto.getUserId());
        if (userResponseDto == null)
            return false;
        recipeRepository.save(modelMapper.map(recipeCreateRequestDto, Recipe.class));
        return true;
    }

    public Boolean updateRecipeById(Long recipeId, RecipeUpdateRequestDto recipeUpdateRequestDto) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (optionalRecipe.isPresent()){
            Recipe recipe = optionalRecipe.get();
            recipe.setText(recipeUpdateRequestDto.getText());
            recipe.setIngredients(recipeUpdateRequestDto.getIngredients());
            recipe.setTitle(recipeUpdateRequestDto.getTitle());
            recipeRepository.save(recipe);
            return true;
        }
        return false;
    }

    public Boolean deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
        return true;
    }
}
