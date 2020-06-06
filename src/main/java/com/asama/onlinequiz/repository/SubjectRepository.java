package com.asama.onlinequiz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

    List<Subject> findTop10ByNameLike(String key);
}
