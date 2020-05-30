package com.asama.onlinequiz.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
    Optional<Student> findByIdAndPassword(String user, String password);
}
