package com.example.security.service.student;

import com.example.security.dto.dictionary.Role;
import com.example.security.dto.mapper.student.UserToDtoMapper;
import com.example.security.dto.student.StudentCreateRequest;
import com.example.security.dto.student.StudentDto;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService {

    UserRepository repository;
    UserToDtoMapper userToDtoMapper = new UserToDtoMapper();

    public StudentService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public StudentDto getStudentById(Long id) {
        User user = repository.findUserByRoleAndId(Role.STUDENT, id)
                .orElseThrow(() -> new RuntimeException("Student was not found with id: " + id));

        return userToDtoMapper.userToStudentDto(user);
    }

    @Transactional(readOnly = true)
    public List<StudentDto> getAllStudents() {
        return userToDtoMapper.usersToStudentDtos(repository.findAll());
    }

    @Transactional()
    public void createStudent(StudentCreateRequest request) {
        User user = new User();
        user.setRole(Role.STUDENT);
        user.setName(request.getName());

        repository.save(user);
    }

    @Transactional()
    public void updateStudent(Long id, StudentCreateRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student was not found with id: " + id));

        user.setName(request.getName());

        repository.save(user);
    }

    @Transactional
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

}
