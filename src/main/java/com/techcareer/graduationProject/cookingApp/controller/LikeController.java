package com.techcareer.graduationProject.cookingApp.controller;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.LikeCreateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.LikeResponseDto;
import com.techcareer.graduationProject.cookingApp.entity.Like;
import com.techcareer.graduationProject.cookingApp.service.LikeService;
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
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;
    private static final Logger logger = LoggerFactory.getLogger(LikeController.class);


    @GetMapping("/getAllLikes")
    public ResponseEntity<List<LikeResponseDto>> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> recipeId) {
        try {
            List<LikeResponseDto> allLikes = likeService.getAllLikes(userId, recipeId);
            if (allLikes != null) {
                return new ResponseEntity<>(allLikes, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            logger.error("An exception occurred in the like controller @GetMapping(\"/getAllLikes\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getLikeById/{likeId}")
    public ResponseEntity<LikeResponseDto> getLikeById(@PathVariable Long likeId) {
        try {
            LikeResponseDto likeById = likeService.getLikeById(likeId);
            if (likeById != null) {
                return new ResponseEntity<>(likeById, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            logger.error("An exception occurred in the like controller @GetMapping(\"/getLikeById/{likeId}\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createLike")
    public ResponseEntity<Boolean> createNewLike(@RequestBody LikeCreateRequestDto likeCreateRequestDto) {
        try {
            Boolean isLikeCreated = likeService.createNewLike(likeCreateRequestDto);
            if (isLikeCreated) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
            }

        } catch (Exception e) {
            logger.error("An exception occurred in the like controller @PostMapping(\"/createLike\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteLike/{likeId}")
    public ResponseEntity<Boolean> deleteLike(@PathVariable Long likeId) { // bu bir body değil tek parametre
        try {
            Boolean isLikeDeleted = likeService.deleteLike(likeId);
            if (isLikeDeleted) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
            }

        } catch (Exception e) {
            logger.error("An exception occurred in the like controller @DeleteMapping(\"/deleteLike/{likeId}\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
