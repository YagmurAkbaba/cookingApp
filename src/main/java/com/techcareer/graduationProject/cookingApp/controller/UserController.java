package com.techcareer.graduationProject.cookingApp.controller;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.UserCreateUpdateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.UserResponseDto;
import com.techcareer.graduationProject.cookingApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<Boolean> createNewUser(@RequestBody UserCreateUpdateRequestDto userCreateUpdateRequestDto){
        Boolean isUserCreated = userService.createNewUser(userCreateUpdateRequestDto);
        return new ResponseEntity<>(isUserCreated,HttpStatus.OK );
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("updateUser/{userId}")
    public ResponseEntity<Boolean> updateUserById(@PathVariable Long userId, @RequestBody UserCreateUpdateRequestDto userCreateUpdateRequestDto){
        Boolean isUserUpdated = userService.updateUserById(userId, userCreateUpdateRequestDto);
        return new ResponseEntity<>(isUserUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId){ // bu bir body değil tek parametre
        Boolean isUserDeleted = userService.deleteUser(userId);
        return new ResponseEntity<>(isUserDeleted, HttpStatus.OK);
    }





}
