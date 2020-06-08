package com.asama.onlinequiz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.ResultTest;

@Repository
public interface ResultTestRepository extends CrudRepository<ResultTest, Long> {

    List<ResultTest> findAllByStudentId(String sid);
}
