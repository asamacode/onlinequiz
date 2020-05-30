package com.asama.onlinequiz.service;

import java.util.Optional;

import com.asama.onlinequiz.model.Lecturer;

public interface LecturerService {
    
    Optional<Lecturer> findByIdAndPass(String userId, String pass);
}
