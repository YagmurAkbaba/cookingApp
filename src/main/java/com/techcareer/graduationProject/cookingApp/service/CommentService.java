package com.techcareer.graduationProject.cookingApp.service;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.CommentCreateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.requestDto.CommentUpdateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.CommentResponseDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.RecipeResponseDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.UserResponseDto;
import com.techcareer.graduationProject.cookingApp.entity.Comment;
import com.techcareer.graduationProject.cookingApp.entity.Recipe;
import com.techcareer.graduationProject.cookingApp.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final RecipeService recipeService;
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);


    public List<CommentResponseDto> getAllComments(Optional<Long> userId, Optional<Long> recipeId) {
        try {
            if (userId.isPresent() && recipeId.isPresent()) {
                if (userService.getUserById(userId.get()) != null && recipeService.getRecipeById(recipeId.get()) != null) {
                    return modelMapper.map(commentRepository.findByUser_UserIdAndRecipe_RecipeId(userId.get(), recipeId.get()), new TypeToken<List<CommentResponseDto>>() {
                    }.getType());
                }
                return null;

            } else if (userId.isPresent()) {
                if (userService.getUserById(userId.get()) != null) {
                    return modelMapper.map(commentRepository.findByUser_UserId(userId.get()), new TypeToken<List<CommentResponseDto>>() {
                    }.getType());
                }
                return null;

            } else if (recipeId.isPresent()) {
                if (recipeService.getRecipeById(recipeId.get()) != null) {
                    return modelMapper.map(commentRepository.findByRecipe_RecipeId(recipeId.get()), new TypeToken<List<CommentResponseDto>>() {
                    }.getType());
                }
                return null;
            } else {
                return modelMapper.map(commentRepository.findAll(), new TypeToken<List<CommentResponseDto>>() {
                }.getType());
            }
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to retrieve comments", e);

        }
    }

    public CommentResponseDto getCommentById(Long commentId) {
        try {
            Optional<Comment> byId = commentRepository.findById(commentId);
            if (byId.isPresent()) {
                return modelMapper.map(byId, CommentResponseDto.class);
            }
            return null;
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to retrieve comment with specified id", e);

        }
    }

    public Boolean createNewComment(CommentCreateRequestDto commentCreateRequestDto) {
        try {
            if (commentCreateRequestDto.getUserId() != null && commentCreateRequestDto.getRecipeId() != null) {
                UserResponseDto userResponseDto = userService.getUserById(commentCreateRequestDto.getUserId());
                RecipeResponseDto recipeResponseDto = recipeService.getRecipeById(commentCreateRequestDto.getRecipeId());

                if (userResponseDto != null && recipeResponseDto != null) {
                    modelMapper.getConfiguration().setAmbiguityIgnored(true);
                    commentRepository.save(modelMapper.map(commentCreateRequestDto, Comment.class));
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to create comment", e);

        }
    }

    public Boolean updateCommentById(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto) {
        try {
            Optional<Comment> optionalComment = commentRepository.findById(commentId);
            if (optionalComment.isPresent()) {
                Comment comment = optionalComment.get();
                comment.setText(commentUpdateRequestDto.getText());
                commentRepository.save(comment);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to update comment", e);

        }
    }

    public Boolean deleteComment(Long commentId) {
        try {
            if (commentRepository.findById(commentId).isPresent()) {
                commentRepository.deleteById(commentId);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to delete comment", e);
        }

    }
}
