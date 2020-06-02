package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import com.asama.onlinequiz.model.Test;

public interface TestService {
    
    Optional<Test> findById(Long id);

    Test update(Test test);

    Test save(Test test);

    List<Test> findAll();

    void delete(Test test);
}
