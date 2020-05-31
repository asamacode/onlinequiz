package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.Lecturer;
import com.asama.onlinequiz.repository.LecturerRepository;

@Service
public class LecturerServiceImpl implements LecturerService {

    @Autowired
    private LecturerRepository repository;

    @Override
    public Optional<Lecturer> findByIdAndPass(String userId, String pass) {
        return repository.findByIdAndPassword(userId, pass);
    }

    @Override
    public Optional<Lecturer> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Lecturer update(Lecturer lecturer) {
        return repository.save(lecturer);
    }

    @Override
    public Lecturer save(Lecturer lecturer) {
        return repository.save(lecturer);
    }

    @Override
    public List<Lecturer> findAll() {
        return (List<Lecturer>) repository.findAll();
    }

    @Override
    public void delete(Lecturer lecturer) {
        repository.delete(lecturer);
    }

}
