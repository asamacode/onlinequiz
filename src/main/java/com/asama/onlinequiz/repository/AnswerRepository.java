package com.asama.onlinequiz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

    List<Answer> findAllByQuestionId(Long id);
}
