package com.asama.onlinequiz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.ResultTest;

@Repository
public interface ResultTestRepository extends CrudRepository<ResultTest, Long> {

}
