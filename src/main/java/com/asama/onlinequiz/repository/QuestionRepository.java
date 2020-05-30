package com.asama.onlinequiz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
