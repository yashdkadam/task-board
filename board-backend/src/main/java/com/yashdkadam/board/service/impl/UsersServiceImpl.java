package com.yashdkadam.board.service.impl;

import com.yashdkadam.board.dto.UserDto;
import com.yashdkadam.board.entity.User;
import com.yashdkadam.board.exception.ResourceNotFoundException;
import com.yashdkadam.board.exception.UserAlreadyExistsException;
import com.yashdkadam.board.mapper.UserMapper;
import com.yashdkadam.board.repository.UsersRepository;
import com.yashdkadam.board.service.IUsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yashdkadam.board.mapper.UserMapper.mapToUserDto;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements IUsersService {
    private UsersRepository usersRepository;

    @Override
    public void createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto, new User());
        Optional<User> optionalCustomer = usersRepository.findByEmail(userDto.getEmail());
        if(optionalCustomer.isPresent()) {
            throw new UserAlreadyExistsException("User already registered with given mobileNumber "
                    +userDto.getEmail());
        }
        System.out.println("user " + user);
        user.setUserId(null);
        usersRepository.save(user);
    }

    @Override
    public List<UserDto> fetchUsers() {
        List<User> Users = usersRepository.findAll();
        List<UserDto> users = new ArrayList<>();
        for(User u: Users){
            UserDto userDto = new UserDto();
            userDto = mapToUserDto(u, userDto);
            users.add(userDto);
        }
        return users;
    }

    @Override
    public UserDto fetchUser(String email) {
        Optional<User> user = usersRepository.findByEmail(email);
        UserDto userDto = mapToUserDto(user.orElse(null), new UserDto());
        return userDto;
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        boolean isUpdated = false;
        if(userDto != null ){
            User user = usersRepository.findById(userDto.getUserId()).orElseThrow(
                    () -> new ResourceNotFoundException("User", "UserId", userDto.getUserId().toString())
            );
            user = UserMapper.mapToUser(userDto, user);
            usersRepository.save(user);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteUser(String email) {
        User user = usersRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", email)
        );
        usersRepository.deleteByUserId(user.getUserId());
        return true;
    }

}
