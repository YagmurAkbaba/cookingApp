package com.techcareer.graduationProject.cookingApp.service;

import com.techcareer.graduationProject.cookingApp.dto.requestDto.UserCreateUpdateRequestDto;
import com.techcareer.graduationProject.cookingApp.dto.responseDto.UserResponseDto;
import com.techcareer.graduationProject.cookingApp.entity.User;
import com.techcareer.graduationProject.cookingApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    /**
     * Dönüş tipleri, özel mesaj dönebiliriz.
     * */

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public List<UserResponseDto> getAllUsers() {
       return modelMapper.map(userRepository.findAll(), new TypeToken<List<UserResponseDto>>() {}.getType());
    }

    public Boolean createNewUser(UserCreateUpdateRequestDto userCreateUpdateRequestDto) {
           userRepository.save(modelMapper.map(userCreateUpdateRequestDto, User.class));
        return true;
    }

    public UserResponseDto getUserById(Long userId) {
       return modelMapper.map(userRepository.findById(userId), UserResponseDto.class);
    }

    public Boolean updateUserById(Long userId, UserCreateUpdateRequestDto userCreateUpdateRequestDto) {
       Optional<User> optionalUser = userRepository.findById(userId);
       if (optionalUser.isPresent()){
           User user = optionalUser.get();
           user.setUserName(userCreateUpdateRequestDto.getUserName());
           user.setPassword(userCreateUpdateRequestDto.getPassword());
           userRepository.save(user);
           return true;
       }
        return false;

    }

    public Boolean deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return true;
    }
}
