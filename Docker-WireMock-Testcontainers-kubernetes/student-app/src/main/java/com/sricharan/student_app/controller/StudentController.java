package com.sricharan.student_app.controller;

import com.sricharan.student_app.dto.LeetCodeProgressResponseDTO;
import com.sricharan.student_app.dto.StudentInputDTO;
import com.sricharan.student_app.dto.StudentOutputDTO;
import com.sricharan.student_app.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    public final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<StudentOutputDTO>> getStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudents());
    }

    @PostMapping("/students")
    public ResponseEntity<StudentOutputDTO> addStudent(@RequestBody StudentInputDTO studentInputDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(studentInputDTO));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentOutputDTO> getStudent(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudent(id));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentOutputDTO> deleteStudent(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(studentService.deleteStudent(id));
    }

    @GetMapping("/students/{id}/lcprofiles")
    public ResponseEntity<LeetCodeProgressResponseDTO> getProfile(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getProgress(id));
    }
}
