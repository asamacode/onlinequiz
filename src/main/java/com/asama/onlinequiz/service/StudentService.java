package com.asama.onlinequiz.service;

import java.util.Optional;

import com.asama.onlinequiz.model.Student;

public interface StudentService {

    Optional<Student> findByIdAndPass(String userId, String pass);
}
