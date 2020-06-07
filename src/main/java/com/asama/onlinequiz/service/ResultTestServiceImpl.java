package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.ResultTest;
import com.asama.onlinequiz.repository.ResultTestRepository;

@Service
public class ResultTestServiceImpl implements ResultTestService {

    @Autowired
    ResultTestRepository resultTestRepository;

    @Override
    public Optional<ResultTest> findById(Long id) {
        return resultTestRepository.findById(id);
    }

    @Override
    public ResultTest update(ResultTest resultTest) {
        return resultTestRepository.save(resultTest);
    }

    @Override
    public ResultTest save(ResultTest resultTest) {
        return resultTestRepository.save(resultTest);
    }

    @Override
    public List<ResultTest> findAll() {
        return (List<ResultTest>) resultTestRepository.findAll();
    }

    @Override
    public void delete(ResultTest resultTest) {
        resultTestRepository.delete(resultTest);
    }

}
