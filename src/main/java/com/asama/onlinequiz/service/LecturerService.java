package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import com.asama.onlinequiz.model.Lecturer;

public interface LecturerService {
    
    Optional<Lecturer> findByIdAndPass(String userId, String pass);
    
    Optional<Lecturer> findById(String id);

    Lecturer update(Lecturer lecturer);

    Lecturer save(Lecturer lecturer);

    List<Lecturer> findAll();

    void delete(Lecturer lecturer);
}
