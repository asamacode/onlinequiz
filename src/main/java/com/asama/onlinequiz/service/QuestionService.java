package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import com.asama.onlinequiz.model.Question;

public interface QuestionService {

    Optional<Question> findById(Long id);

    Question update(Question question);

    Question save(Question question);

    List<Question> findAll();
    
    List<Question> findAllByLecturerId(String id);

    void delete(Question question);
}
