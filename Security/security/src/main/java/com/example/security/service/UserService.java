package com.example.security.service;

import com.example.security.dto.UserDto;
import com.example.security.dto.mapper.UserToDtoMapper;
import com.example.security.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserToDtoMapper userToDtoMapper = new UserToDtoMapper();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userToDtoMapper.usersToDtos(userRepository.findAll());
    }

}
