package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import com.asama.onlinequiz.model.AppClass;

public interface AppClassService {

    Optional<AppClass> findById(Long id);

    AppClass update(AppClass appClass);

    AppClass save(AppClass appClass);

    List<AppClass> findAll();

    void delete(AppClass appClass);
}
