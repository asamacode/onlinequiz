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

    List<Question> findAllBySubjectIdDif(Long subId, Integer dif);

    List<Question> findAllBySubjectIdNor(Long subId, Integer nor);

    List<Question> findAllBySubjectIdEasy(Long subId, Integer easy);

    List<Question> searchByKeyAndSubject(String key, Long subId);
}
