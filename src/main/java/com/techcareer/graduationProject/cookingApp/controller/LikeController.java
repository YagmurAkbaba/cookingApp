package com.techcareer.graduationProject.cookingApp.controller;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.LikeCreateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.LikeResponseDto;
import com.techcareer.graduationProject.cookingApp.entity.Like;
import com.techcareer.graduationProject.cookingApp.service.LikeService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/getAllLikes")
    public ResponseEntity<List<LikeResponseDto>> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> recipeId){
        return new ResponseEntity<>(likeService.getAllLikes(userId, recipeId), HttpStatus.OK);
    }

    @GetMapping("/getLikeById/{likeId}")
    public ResponseEntity<LikeResponseDto> getLikeById(@PathVariable Long likeId){
        return new ResponseEntity<>(likeService.getLikeById(likeId), HttpStatus.OK);
    }

    @PostMapping("/createLike")
    public ResponseEntity<Boolean> createNewLike(@RequestBody LikeCreateRequestDto likeCreateRequestDto){
        Boolean isLikeCreated = likeService.createNewLike(likeCreateRequestDto);
        return new ResponseEntity<>(isLikeCreated,HttpStatus.OK );
    }


    @DeleteMapping("/deleteLike/{likeId}")
    public ResponseEntity<Boolean> deleteLike(@PathVariable Long likeId){ // bu bir body değil tek parametre
        Boolean isLikeDeleted = likeService.deleteLike(likeId);
        return new ResponseEntity<>(isLikeDeleted, HttpStatus.OK);
    }

}
