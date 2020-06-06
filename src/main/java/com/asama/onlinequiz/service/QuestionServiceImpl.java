package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<Question> findAllBySubjectIdDif(Long subId, Integer dif) {
        Pageable pageable = PageRequest.of(0, dif);
        return questionRepository.findAllBySubjectId(subId, Question.LV_HARD, pageable);
    }

    @Override
    public List<Question> findAllBySubjectIdNor(Long subId, Integer nor) {
        Pageable pageable = PageRequest.of(0, nor);
        return questionRepository.findAllBySubjectId(subId, Question.LV_NORMAL, pageable);
    }

    @Override
    public List<Question> findAllBySubjectIdEasy(Long subId, Integer easy) {
        Pageable pageable = PageRequest.of(0, easy);
        return questionRepository.findAllBySubjectId(subId, Question.LV_EASY, pageable);
    }

    @Override
    public List<Question> searchByKeyAndSubject(String key, Long subId) {
        return questionRepository.findTop10ByContentLikeAndSubjectId("%"+key+"%", subId);
    }

}

