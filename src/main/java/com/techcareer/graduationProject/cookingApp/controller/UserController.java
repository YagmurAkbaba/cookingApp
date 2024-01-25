package com.techcareer.graduationProject.cookingApp.controller;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.UserCreateUpdateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.UserResponseDto;
import com.techcareer.graduationProject.cookingApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An exception occurred in the user controller @GetMapping(\"/getAllUsers\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<Boolean> createNewUser(@RequestBody UserCreateUpdateRequestDto userCreateUpdateRequestDto) {
        try {
            Boolean isUserCreated = userService.createNewUser(userCreateUpdateRequestDto);
            if (isUserCreated) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            logger.error("An exception occurred in the user controller @PostMapping(\"/createUser\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {
        try {
            UserResponseDto userById = userService.getUserById(userId);
            if (userById != null) {
                return new ResponseEntity<>(userById, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
            }


        } catch (Exception e) {
            logger.error("An exception occurred in the user controller @GetMapping(\"/getUserById/{userId}\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<Boolean> updateUserById(@PathVariable Long userId, @RequestBody UserCreateUpdateRequestDto userCreateUpdateRequestDto) {
        try {
            Boolean isUserUpdated = userService.updateUserById(userId, userCreateUpdateRequestDto);
            if (isUserUpdated) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            logger.error("An exception occurred in the user controller @PutMapping(\"/updateUser/{userId}\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) { // bu bir body değil tek parametre
        try {
            Boolean isUserDeleted = userService.deleteUser(userId);
            if (isUserDeleted) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            logger.error("An exception occurred in the user controller @DeleteMapping(\"/deleteUser/{userId}\"):", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
