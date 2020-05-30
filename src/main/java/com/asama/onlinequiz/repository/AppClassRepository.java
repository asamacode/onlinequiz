package com.asama.onlinequiz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.AppClass;

@Repository
public interface AppClassRepository extends CrudRepository<AppClass, Long> {

}
