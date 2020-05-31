package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import com.asama.onlinequiz.model.SchoolYear;

public interface SchoolYearService {

    Optional<SchoolYear> findById(Long id);
    
    SchoolYear update(SchoolYear schoolYear);
    
    SchoolYear save(SchoolYear schoolYear);
    
    List<SchoolYear> findAll();
    
    void delete(SchoolYear schoolYear);
    
}
