package com.yashdkadam.board.service;

import com.yashdkadam.board.dto.UserDto;

import java.util.List;

public interface IUsersService {

    void createUser(UserDto userDto);

    List<UserDto> fetchUsers();

    UserDto fetchUser(String email);

    boolean updateUser(UserDto userDto);

    boolean deleteUser(String email);
}
