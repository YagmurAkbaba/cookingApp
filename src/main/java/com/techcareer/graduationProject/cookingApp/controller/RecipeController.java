package com.techcareer.graduationProject.cookingApp.controller;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.RecipeCreateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.requestDto.RecipeUpdateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.RecipeResponseDto;
import com.techcareer.graduationProject.cookingApp.entity.Recipe;
import com.techcareer.graduationProject.cookingApp.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe")
@CrossOrigin // to reach it from localhost:3000
public class RecipeController {
    private final RecipeService recipeService;
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    // if userId given returns user's recipes, else returns all recipes
    @GetMapping("/getAllRecipes")
    public ResponseEntity<List<RecipeResponseDto>> getAllRecipes(@RequestParam Optional<Long> userId) {
        try {
            List<RecipeResponseDto> allRecipes = recipeService.getAllRecipes(userId);
            if (allRecipes != null) {
                return new ResponseEntity<>(allRecipes, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            logger.error("An exception occurred in the recipe controller @GetMapping(\"/getAllRecipes\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getRecipeById/{recipeId}")
    public ResponseEntity<RecipeResponseDto> getRecipeById(@PathVariable Long recipeId) {
        try {
            RecipeResponseDto recipeById = recipeService.getRecipeById(recipeId);
            if (recipeById != null) {
                return new ResponseEntity<>(recipeById, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            logger.error("An exception occurred in the recipe controller @GetMapping(\"/getRecipeById/{recipeId}\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createRecipe")
    public ResponseEntity<Boolean> createNewRecipe(@RequestBody RecipeCreateRequestDto userCreateRequestDto) {
        try {
            Boolean isRecipeCreated = recipeService.createNewRecipe(userCreateRequestDto);
            if (isRecipeCreated) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            logger.error("An exception occurred in the recipe controller  @PostMapping(\"/createRecipe\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateRecipe/{recipeId}")
    public ResponseEntity<Boolean> updateRecipeById(@PathVariable Long recipeId, @RequestBody RecipeUpdateRequestDto userUpdateRequestDto) {
        try {
            Boolean isUserUpdated = recipeService.updateRecipeById(recipeId, userUpdateRequestDto);
            if (isUserUpdated) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            logger.error("An exception occurred in the recipe controller @PutMapping(\"/updateRecipe/{recipeId}\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteRecipe/{recipeId}")
    public ResponseEntity<Boolean> deleteRecipe(@PathVariable Long recipeId) { // bu bir body değil tek parametre
        try {
            Boolean isRecipeDeleted = recipeService.deleteRecipe(recipeId);
            if (isRecipeDeleted) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            logger.error("An exception occurred in the recipe controller @DeleteMapping(\"/deleteRecipe/{recipeId}\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
