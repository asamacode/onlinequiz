package com.asama.onlinequiz.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.Lecturer;

@Repository
public interface LecturerRepository extends CrudRepository<Lecturer, String> {
    
    Optional<Lecturer> findByIdAndPassword(String user, String password);
}
