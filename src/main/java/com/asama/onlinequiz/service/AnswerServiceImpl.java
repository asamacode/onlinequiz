package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.Answer;
import com.asama.onlinequiz.repository.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public Optional<Answer> findById(Long id) {
        return answerRepository.findById(id);
    }

    @Override
    public Answer update(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public List<Answer> findAll() {
        return (List<Answer>) answerRepository.findAll();
    }

    @Override
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    @Override
    public void saveAll(Set<Answer> answers) {
       answerRepository.saveAll(answers);
    }

    @Override
    public List<Answer> findAllByQuestionId(Long id) {
       return answerRepository.findAllByQuestionId(id);
    }

}
