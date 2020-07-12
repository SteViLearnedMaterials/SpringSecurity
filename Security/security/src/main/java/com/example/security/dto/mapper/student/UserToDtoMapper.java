package com.example.security.dto.mapper.student;

import com.example.security.dto.student.StudentDto;
import com.example.security.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserToDtoMapper {

    public List<StudentDto> usersToStudentDtos(List<User> users) {
        return users.stream()
                .map(this::getStudentDto)
                .collect(Collectors.toList());
    }

    public StudentDto userToStudentDto(User user) {
        return getStudentDto(user);
    }

    private StudentDto getStudentDto(User user) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(user.getId());
        studentDto.setName(user.getName());

        return studentDto;
    }
}
