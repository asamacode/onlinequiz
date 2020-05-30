package com.asama.onlinequiz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.SchoolYear;

@Repository
public interface SchoolYearRepository extends CrudRepository<SchoolYear, Long> {

}
