package com.techcareer.graduationProject.cookingApp.controller;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.AuthRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.requestDto.UserCreateUpdateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.AuthResponseDto;
import com.techcareer.graduationProject.cookingApp.entity.User;
import com.techcareer.graduationProject.cookingApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @GetMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestParam String userName, @RequestParam String password) {
        try {
            User user = userService.getUserByUserName(userName);
            if (user != null && user.getPassword().equals(password)) {
                AuthResponseDto success = modelMapper.map(user, AuthResponseDto.class);
                return new ResponseEntity<>(success, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            logger.error("An exception occurred during login:", e);

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody AuthRequestDto registerRequest) {
        try {
            User user = userService.getUserByUserName(registerRequest.getUserName());
            UserCreateUpdateRequestDto userCreateUpdateRequestDto = modelMapper.map(registerRequest, UserCreateUpdateRequestDto.class);

            if (user == null) {
                userService.createNewUser(userCreateUpdateRequestDto);
                User user2 = userService.getUserByUserName(registerRequest.getUserName());
                return new ResponseEntity<>(modelMapper.map(user2, AuthResponseDto.class), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.error("An exception occurred during register:", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
