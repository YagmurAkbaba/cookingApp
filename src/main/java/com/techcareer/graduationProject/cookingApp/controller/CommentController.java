package com.techcareer.graduationProject.cookingApp.controller;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.CommentCreateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.requestDto.CommentUpdateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.CommentResponseDto;
import com.techcareer.graduationProject.cookingApp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/getAllComments")
    public ResponseEntity<List<CommentResponseDto>> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> recipeId){
        return new ResponseEntity<>(commentService.getAllComments(userId, recipeId), HttpStatus.OK);
    }

    @GetMapping("/getCommentById/{commentId}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
    }

    @PostMapping("/createComment")
    public ResponseEntity<Boolean> createNewComment(@RequestBody CommentCreateRequestDto commentCreateRequestDto){
        Boolean isCommentCreated = commentService.createNewComment(commentCreateRequestDto);
        return new ResponseEntity<>(isCommentCreated,HttpStatus.OK );
    }

    @PutMapping("/updateComment/{commentId}")
    public ResponseEntity<Boolean> updateCommentById(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto){
        Boolean isCommentUpdated = commentService.updateCommentById(commentId, commentUpdateRequestDto);
        return new ResponseEntity<>(isCommentUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentId){ // bu bir body değil tek parametre
        Boolean isCommentDeleted = commentService.deleteComment(commentId);
        return new ResponseEntity<>(isCommentDeleted, HttpStatus.OK);
    }
}
