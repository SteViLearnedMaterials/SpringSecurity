package com.example.security.controller.student;

import com.example.security.dto.student.StudentCreateRequest;
import com.example.security.dto.student.StudentDto;
import com.example.security.service.student.StudentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentManagementController {

    StudentService studentService;

    public StudentManagementController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public void createStudent(@RequestBody StudentCreateRequest createRequest) {
        studentService.createStudent(createRequest);
    }

    @PutMapping("{studentId}")
    public void updateStudent(@PathVariable Long studentId, @RequestBody StudentCreateRequest createRequest) {
        studentService.updateStudent(studentId, createRequest);
    }

    @DeleteMapping("{studentId}")
    public void updateStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }

}
