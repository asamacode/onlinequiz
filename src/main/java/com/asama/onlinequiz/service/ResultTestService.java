package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import com.asama.onlinequiz.model.ResultTest;

public interface ResultTestService {


    Optional<ResultTest> findById(Long id);
    
    ResultTest update(ResultTest resultTest);
    
    ResultTest save(ResultTest resultTest);
    
    List<ResultTest> findAll();
    
    void delete(ResultTest resultTest);
}
