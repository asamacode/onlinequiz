package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.Student;
import com.asama.onlinequiz.model.Test;
import com.asama.onlinequiz.repository.TestRepository;

@Service
public class TestServiceImpl implements TestService {
    
    @Autowired
    TestRepository testRepository;

    @Override
    public Optional<Test> findById(Long id) {
        return testRepository.findById(id);
    }

    @Override
    public Test update(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test save(Test test) {
        return testRepository.save(test);
    }

    @Override
    public List<Test> findAll() {
        return (List<Test>) testRepository.findAll();
    }

    @Override
    public void delete(Test test) {
        testRepository.delete(test);
    }

    @Override
    public List<Test> findAllListTestByStudent(Student student) {
        return testRepository.findListTestStudentId(student.getAppClass().getId());
    }

}
