package com.techcareer.graduationProject.cookingApp.controller;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.CommentCreateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.requestDto.CommentUpdateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.CommentResponseDto;
import com.techcareer.graduationProject.cookingApp.service.CommentService;
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
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);


    @GetMapping("/getAllComments")
    public ResponseEntity<List<CommentResponseDto>> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> recipeId) {
        try {
            List<CommentResponseDto> allComments = commentService.getAllComments(userId, recipeId);
            if (allComments != null) {
                return new ResponseEntity<>(allComments, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            logger.error("An exception occurred in the comment controller @GetMapping(\"/getAllComments\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCommentById/{commentId}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Long commentId) {
        try {
            CommentResponseDto commentById = commentService.getCommentById(commentId);
            if (commentById != null) {
                return new ResponseEntity<>(commentById, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            logger.error("An exception occurred in the comment controller @GetMapping(\"/getCommentById/{commentId}\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createComment")
    public ResponseEntity<Boolean> createNewComment(@RequestBody CommentCreateRequestDto commentCreateRequestDto) {
        try {
            Boolean isCommentCreated = commentService.createNewComment(commentCreateRequestDto);
            if (isCommentCreated) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
            }

        } catch (Exception e) {
            logger.error("An exception occurred in the comment controller @PostMapping(\"/createComment\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateComment/{commentId}")
    public ResponseEntity<Boolean> updateCommentById(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        try {
            Boolean isCommentUpdated = commentService.updateCommentById(commentId, commentUpdateRequestDto);
            if (isCommentUpdated) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            logger.error("An exception occurred in the comment controller @PutMapping(\"/updateComment/{commentId}\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentId) { // bu bir body değil tek parametre
        try {
            Boolean isCommentDeleted = commentService.deleteComment(commentId);
            if (isCommentDeleted) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            logger.error("An exception occurred in the comment controller @DeleteMapping(\"/deleteComment/{commentId}\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
