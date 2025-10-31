package com.yashdkadam.board.dto;

import com.yashdkadam.board.entity.User;
import lombok.Data;

@Data
public class UserDto {
    private Long userId;

    private String username;

    private String email;

    private String passwordHash;

    private User.Role role;

}
