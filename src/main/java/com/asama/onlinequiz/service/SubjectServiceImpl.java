package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.Subject;
import com.asama.onlinequiz.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Subject update(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> findAll() {
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }

}
