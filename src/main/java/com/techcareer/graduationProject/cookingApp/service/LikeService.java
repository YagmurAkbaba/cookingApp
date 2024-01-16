package com.techcareer.graduationProject.cookingApp.service;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.LikeCreateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.LikeResponseDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.RecipeResponseDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.UserResponseDto;
import com.techcareer.graduationProject.cookingApp.entity.Like;
import com.techcareer.graduationProject.cookingApp.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final RecipeService recipeService;

    public List<LikeResponseDto> getAllLikes(Optional<Long> userId, Optional<Long> recipeId) {
        if (userId.isPresent() && recipeId.isPresent()){
            return modelMapper.map(likeRepository.findByUser_UserIdAndRecipe_RecipeId(userId.get(), recipeId.get()), new TypeToken<List<LikeResponseDto>>() {
            }.getType());

        }else if(userId.isPresent()){
            return modelMapper.map(likeRepository.findByUser_UserId(userId.get()), new TypeToken<List<LikeResponseDto>>() {
            }.getType());
        }else if(recipeId.isPresent()){
            return modelMapper.map(likeRepository.findByRecipe_RecipeId(recipeId.get()), new TypeToken<List<LikeResponseDto>>() {
            }.getType());
        }else{
            return modelMapper.map(likeRepository.findAll(), new TypeToken<List<LikeResponseDto>>() {
            }.getType());
        }
    }

    public LikeResponseDto getLikeById(Long likeId) {
        return modelMapper.map(likeRepository.findById(likeId), LikeResponseDto.class);
    }

    public Boolean createNewLike(LikeCreateRequestDto likeCreateRequestDto) {
        UserResponseDto userResponseDto = userService.getUserById(likeCreateRequestDto.getUserId());
        RecipeResponseDto recipeResponseDto = recipeService.getRecipeById(likeCreateRequestDto.getRecipeId());
        if (userResponseDto != null && recipeResponseDto != null){
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            likeRepository.save(modelMapper.map(likeCreateRequestDto, Like.class));
            return true;
        }
        return false;
    }


    public Boolean deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
        return true;
    }
}
