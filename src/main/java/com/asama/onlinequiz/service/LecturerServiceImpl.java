package com.asama.onlinequiz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.Lecturer;
import com.asama.onlinequiz.repository.LecturerRepository;

@Service
public class LecturerServiceImpl implements LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;
    
    @Override
    public Optional<Lecturer> findByIdAndPass(String userId, String pass) {
        return lecturerRepository.findByIdAndPassword(userId, pass);
    }

}
