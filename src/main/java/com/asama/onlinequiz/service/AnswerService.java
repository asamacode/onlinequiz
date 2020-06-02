package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.asama.onlinequiz.model.Answer;

public interface AnswerService {

    Optional<Answer> findById(Long id);

    Answer update(Answer answer);

    Answer save(Answer answer);

    List<Answer> findAll();

    void delete(Answer answer);
    
    void saveAll(Set<Answer> answers);

    List<Answer> findAllByQuestionId(Long id);
}
