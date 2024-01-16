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

    public List<CommentResponseDto> getAllComments(Optional<Long> userId, Optional<Long> recipeId) {
        if (userId.isPresent() && recipeId.isPresent()){
           return modelMapper.map(commentRepository.findByUser_UserIdAndRecipe_RecipeId(userId.get(), recipeId.get()), new TypeToken<List<CommentResponseDto>>() {
            }.getType());

        }else if(userId.isPresent()){
            return modelMapper.map(commentRepository.findByUser_UserId(userId.get()), new TypeToken<List<CommentResponseDto>>() {
            }.getType());
        }else if(recipeId.isPresent()){
            return modelMapper.map(commentRepository.findByRecipe_RecipeId(recipeId.get()), new TypeToken<List<CommentResponseDto>>() {
            }.getType());
        }else{
            return modelMapper.map(commentRepository.findAll(), new TypeToken<List<CommentResponseDto>>() {
            }.getType());
        }
    }

    public CommentResponseDto getCommentById(Long commentId) {
        return modelMapper.map(commentRepository.findById(commentId), CommentResponseDto.class);
    }

    public Boolean createNewComment(CommentCreateRequestDto commentCreateRequestDto) {
        UserResponseDto userResponseDto = userService.getUserById(commentCreateRequestDto.getUserId());
        RecipeResponseDto recipeResponseDto = recipeService.getRecipeById(commentCreateRequestDto.getRecipeId());
        if (userResponseDto != null && recipeResponseDto != null){
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            commentRepository.save(modelMapper.map(commentCreateRequestDto, Comment.class));
            return true;
        }
        return false;
    }

    public Boolean updateCommentById(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()){
            Comment comment = optionalComment.get();
            comment.setText(commentUpdateRequestDto.getText());
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    public Boolean deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return true;
    }
}
