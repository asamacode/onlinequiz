package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.Question;
import com.asama.onlinequiz.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
    
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question update(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> findAll() {
        return (List<Question>) questionRepository.findAll();
    }

    @Override
    public void delete(Question question) {
        questionRepository.delete(question);
    }

    @Override
    public List<Question> findAllByLecturerId(String id) {
        return questionRepository.findAllByLecturerId(id);
    }

}

