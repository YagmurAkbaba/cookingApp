package com.techcareer.graduationProject.cookingApp.controller;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.RecipeCreateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.requestDto.RecipeUpdateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.RecipeResponseDto;
import com.techcareer.graduationProject.cookingApp.service.RecipeService;
import lombok.RequiredArgsConstructor;
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

    // if userId given returns user's recipes, else returns all recipes
    @GetMapping("/getAllRecipes")
    public ResponseEntity<List<RecipeResponseDto>> getAllRecipes(@RequestParam Optional<Long> userId){
        return new ResponseEntity<>(recipeService.getAllRecipes(userId), HttpStatus.OK);
    }

    @GetMapping("/getRecipeById/{recipeId}")
    public ResponseEntity<RecipeResponseDto> getRecipeById(@PathVariable Long recipeId){
        return new ResponseEntity<>(recipeService.getRecipeById(recipeId), HttpStatus.OK);
    }

    @PostMapping("/createRecipe")
    public ResponseEntity<Boolean> createNewRecipe(@RequestBody RecipeCreateRequestDto userCreateRequestDto){
        Boolean isRecipeCreated = recipeService.createNewRecipe(userCreateRequestDto);
        return new ResponseEntity<>(isRecipeCreated,HttpStatus.OK );
    }

    @PutMapping("/updateRecipe/{recipeId}")
    public ResponseEntity<Boolean> updateRecipeById(@PathVariable Long recipeId, @RequestBody RecipeUpdateRequestDto userUpdateRequestDto){
        Boolean isUserUpdated = recipeService.updateRecipeById(recipeId, userUpdateRequestDto);
        return new ResponseEntity<>(isUserUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteRecipe/{recipeId}")
    public ResponseEntity<Boolean> deleteRecipe(@PathVariable Long recipeId){ // bu bir body değil tek parametre
        Boolean isRecipeDeleted = recipeService.deleteRecipe(recipeId);
        return new ResponseEntity<>(isRecipeDeleted, HttpStatus.OK);
    }





}
