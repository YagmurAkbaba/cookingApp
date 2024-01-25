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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final RecipeService recipeService;
    private static final Logger logger = LoggerFactory.getLogger(LikeService.class);

    public List<LikeResponseDto> getAllLikes(Optional<Long> userId, Optional<Long> recipeId) {
        try {
            if (userId.isPresent() && recipeId.isPresent()) {
                if (userService.getUserById(userId.get()) != null && recipeService.getRecipeById(recipeId.get()) != null) {
                    Like like = likeRepository.findByUser_UserIdAndRecipe_RecipeId(userId.get(), recipeId.get());
                    if (like != null) {
                        LikeResponseDto resp = modelMapper.map(like, LikeResponseDto.class);
                        List<LikeResponseDto> responseDto = new ArrayList<>();
                        responseDto.add(resp);
                        return responseDto;
                    }


                }
                return null;

            } else if (userId.isPresent()) {
                if (userService.getUserById(userId.get()) != null) {
                    return modelMapper.map(likeRepository.findByUser_UserId(userId.get()), new TypeToken<List<LikeResponseDto>>() {
                    }.getType());
                }
                return null;
            } else if (recipeId.isPresent()) {
                if (recipeService.getRecipeById(recipeId.get()) != null) {
                    return modelMapper.map(likeRepository.findByRecipe_RecipeId(recipeId.get()), new TypeToken<List<LikeResponseDto>>() {
                    }.getType());
                }
                return null;
            } else {
                return modelMapper.map(likeRepository.findAll(), new TypeToken<List<LikeResponseDto>>() {
                }.getType());
            }
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to retrieve likes", e);

        }
    }

    public LikeResponseDto getLikeById(Long likeId) {
        try {
            Optional<Like> byId = likeRepository.findById(likeId);
            if (byId.isPresent()) {
                return modelMapper.map(byId, LikeResponseDto.class);
            }
            return null;


        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to retrieve like with specified id", e);

        }
    }

    public Boolean createNewLike(LikeCreateRequestDto likeCreateRequestDto) {
        try {
            if (likeCreateRequestDto.getUserId() != null && likeCreateRequestDto.getRecipeId() != null) {
                UserResponseDto userResponseDto = userService.getUserById(likeCreateRequestDto.getUserId());
                RecipeResponseDto recipeResponseDto = recipeService.getRecipeById(likeCreateRequestDto.getRecipeId());
                Like like = likeRepository.findByUser_UserIdAndRecipe_RecipeId(likeCreateRequestDto.getUserId(), likeCreateRequestDto.getRecipeId());

                if (userResponseDto != null && recipeResponseDto != null && like == null) {
                    modelMapper.getConfiguration().setAmbiguityIgnored(true);
                    likeRepository.save(modelMapper.map(likeCreateRequestDto, Like.class));
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to create like", e);

        }
    }


    public Boolean deleteLike(Long likeId) {
        try {
            if (likeRepository.findById(likeId).isPresent()) {
                likeRepository.deleteById(likeId);
                return true;
            }
            return false;

        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to delete like", e);
        }
    }
}
