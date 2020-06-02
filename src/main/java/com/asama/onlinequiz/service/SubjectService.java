package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import com.asama.onlinequiz.model.Subject;

public interface SubjectService {

    Optional<Subject> findById(Long id);

    Subject update(Subject subject);

    Subject save(Subject subject);

    List<Subject> findAll();

    void delete(Subject subject);

}
