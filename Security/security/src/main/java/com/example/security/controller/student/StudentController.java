package com.example.security.controller.student;

import com.example.security.dto.student.StudentDto;
import com.example.security.service.student.StudentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }
}
