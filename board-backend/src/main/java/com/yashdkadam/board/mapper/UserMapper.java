package com.yashdkadam.board.mapper;

import com.yashdkadam.board.dto.UserDto;
import com.yashdkadam.board.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user, UserDto userDto){

        if (user == null) return null;

        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole()); // Convert enum to String

        return userDto;

    }

    public static User mapToUser(UserDto userDto, User user) {

        if (userDto == null) return null;

        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPasswordHash());

        // Assuming Role is an enum and stored as a String in UserDto
        if (userDto.getRole() != null) {
            user.setRole(userDto.getRole());
        }

        return user;
    }
}
