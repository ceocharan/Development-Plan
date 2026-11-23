package com.sricharan.student_app.repository;

import com.sricharan.student_app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByActiveTrue();
    Optional<Student> findByIdAndActiveTrue(int id);

    @Query("SELECT s.leetCodeUserName FROM Student s WHERE s.id = :id AND s.active = true")
    Optional<String> findNameById(int id);
}
