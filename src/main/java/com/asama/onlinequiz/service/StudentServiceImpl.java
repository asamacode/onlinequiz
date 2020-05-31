package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.Student;
import com.asama.onlinequiz.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository respository;
    
    @Override
    public Optional<Student> findByIdAndPass(String userId, String pass) {
        return respository.findByIdAndPassword(userId, pass);
    }

    @Override
    public Optional<Student> findById(String id) {
        return respository.findById(id);
    }

    @Override
    public Student update(Student student) {
        return respository.save(student);
    }

    @Override
    public Student save(Student student) {
        return respository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return (List<Student>) respository.findAll();
    }

    @Override
    public void delete(Student student) {
        respository.delete(student);
    }

}
