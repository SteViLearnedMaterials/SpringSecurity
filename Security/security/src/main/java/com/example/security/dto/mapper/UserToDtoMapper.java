package com.example.security.dto.mapper;

import com.example.security.dto.UserDto;
import com.example.security.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserToDtoMapper {

    public List<UserDto> usersToDtos(List<User> users) {
        return users.stream()
                .map(this::getUserDto)
                .collect(Collectors.toList());
    }

    public UserDto userToDto(User user) {
        return getUserDto(user);
    }

    private UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());

        return userDto;
    }
}
