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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    public List<RecipeResponseDto> getAllRecipes(Optional<Long> userId) {
        try {
            if (userId.isPresent()) {
                UserResponseDto userById = userService.getUserById(userId.get());
                if (userById != null) {
                    return modelMapper.map(recipeRepository.findByUser_UserId(userId.get()), new TypeToken<List<RecipeResponseDto>>() {
                    }.getType());
                }
                return null;
            }

            return modelMapper.map(recipeRepository.findAll(), new TypeToken<List<RecipeResponseDto>>() {
            }.getType());
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to retrieve recipes", e);

        }
    }

    public RecipeResponseDto getRecipeById(Long recipeId) {
        try {
            Optional<Recipe> byId = recipeRepository.findById(recipeId);
            if (byId.isPresent()) {
                return modelMapper.map(byId, RecipeResponseDto.class);
            }
            return null;

        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to retrieve recipe with specified id", e);

        }
    }

    public Boolean createNewRecipe(RecipeCreateRequestDto recipeCreateRequestDto) {
        try {
            UserResponseDto userResponseDto = userService.getUserById(recipeCreateRequestDto.getUserId());
            System.out.println("user response dto");
            System.out.println(userResponseDto);
            if (userResponseDto == null)
                return false;
            recipeRepository.save(modelMapper.map(recipeCreateRequestDto, Recipe.class));
            return true;
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to create recipe", e);

        }
    }

    public Boolean updateRecipeById(Long recipeId, RecipeUpdateRequestDto recipeUpdateRequestDto) {
        try {
            Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
            if (optionalRecipe.isPresent()) {
                Recipe recipe = optionalRecipe.get();
                recipe.setText(recipeUpdateRequestDto.getText());
                recipe.setIngredients(recipeUpdateRequestDto.getIngredients());
                recipe.setTitle(recipeUpdateRequestDto.getTitle());
                recipeRepository.save(recipe);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to update recipe", e);

        }
    }

    public Boolean deleteRecipe(Long recipeId) {
        try {
            if (recipeRepository.findById(recipeId).isPresent()) {
                recipeRepository.deleteById(recipeId);
                return true;
            }

            return false;

        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to delete recipe", e);
        }
    }
}
