package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import com.asama.onlinequiz.model.Specialization;

public interface SpecializationService {

    Optional<Specialization> findById(Long id);
    
    Specialization update(Specialization speicalization);
    
    Specialization save(Specialization speicalization);
    
    List<Specialization> findAll();
    
    void delete(Specialization speicalization);
}
