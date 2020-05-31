package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import com.asama.onlinequiz.model.Student;

public interface StudentService {

    Optional<Student> findByIdAndPass(String userId, String pass);
    
    Optional<Student> findById(String id);

    Student update(Student student);

    Student save(Student student);

    List<Student> findAll();

    void delete(Student student);
}
