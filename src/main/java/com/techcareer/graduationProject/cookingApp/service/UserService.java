package com.techcareer.graduationProject.cookingApp.service;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.UserCreateUpdateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.UserResponseDto;
import com.techcareer.graduationProject.cookingApp.entity.User;
import com.techcareer.graduationProject.cookingApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    /**
     * Dönüş tipleri, özel mesaj dönebiliriz.
     */

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public List<UserResponseDto> getAllUsers() {
        try {
            return modelMapper.map(userRepository.findAll(), new TypeToken<List<UserResponseDto>>() {
            }.getType());
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to retrieve users", e);

        }
    }

    public Boolean createNewUser(UserCreateUpdateRequestDto userCreateUpdateRequestDto) {
        try {
            if (userCreateUpdateRequestDto.getPassword() != null && userCreateUpdateRequestDto.getUserName() != null) {
                userRepository.save(modelMapper.map(userCreateUpdateRequestDto, User.class));
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to create user", e);

        }
    }

    public UserResponseDto getUserById(Long userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                return modelMapper.map(user, UserResponseDto.class);
            } else {
                return null;
            }

        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to retrieve user with specified id", e);

        }
    }

    public User getUserByUserName(String userName) {
        try {
            return userRepository.findByUserName(userName);
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to retrieve user with specified username", e);

        }
    }

    public Boolean updateUserById(Long userId, UserCreateUpdateRequestDto userCreateUpdateRequestDto) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setUserName(userCreateUpdateRequestDto.getUserName());
                user.setPassword(userCreateUpdateRequestDto.getPassword());
                userRepository.save(user);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to update user", e);

        }

    }

    public Boolean deleteUser(Long userId) {
        try {
            if (userRepository.findById(userId).isPresent()) {
                userRepository.deleteById(userId);
                return true;
            }
            return false;

        } catch (Exception e) {
            logger.error("An exception occurred:", e);
            throw new RuntimeException("Failed to delete recipe", e);
        }
    }
}
