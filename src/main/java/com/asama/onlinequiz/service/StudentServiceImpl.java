package com.asama.onlinequiz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.Student;
import com.asama.onlinequiz.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Override
    public Optional<Student> findByIdAndPass(String userId, String pass) {
        return studentRepository.findByIdAndPassword(userId, pass);
    }

}
