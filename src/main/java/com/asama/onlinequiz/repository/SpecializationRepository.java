package com.asama.onlinequiz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.Specialization;

@Repository
public interface SpecializationRepository extends CrudRepository<Specialization, Long> {

}
