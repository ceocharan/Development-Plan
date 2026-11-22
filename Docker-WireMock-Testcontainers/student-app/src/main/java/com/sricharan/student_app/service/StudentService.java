package com.sricharan.student_app.service;

import com.sricharan.student_app.dto.LeetCodeProgressResponseDTO;
import com.sricharan.student_app.dto.StudentInputDTO;
import com.sricharan.student_app.dto.StudentOutputDTO;
import com.sricharan.student_app.entity.Student;
import com.sricharan.student_app.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final LeetCodeService leetCodeService;
    private final ObjectMapper objectMapper;

    public List<StudentOutputDTO> getStudents() {
        return studentRepository.findByActiveTrue().stream().map(student -> objectMapper.convertValue(student, StudentOutputDTO.class)).toList();
    }

    public StudentOutputDTO addStudent(StudentInputDTO studentInputDTO) {
        Student student = objectMapper.convertValue(studentInputDTO, Student.class);
        studentRepository.save(student);
        return objectMapper.convertValue(student, StudentOutputDTO.class);
    }

    public StudentOutputDTO getStudent(int id) {
        return objectMapper.convertValue(studentRepository.findByIdAndActiveTrue(id), StudentOutputDTO.class);
    }

    public StudentOutputDTO deleteStudent(int id) {
        Student student = studentRepository.findByIdAndActiveTrue(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setActive(false);
        studentRepository.save(student);
        return objectMapper.convertValue(student, StudentOutputDTO.class);
    }

    public LeetCodeProgressResponseDTO getProgress(int studentId) {
        String leetCodeUsername = studentRepository.findNameById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        return leetCodeService.getProgress(leetCodeUsername);
    }
}
