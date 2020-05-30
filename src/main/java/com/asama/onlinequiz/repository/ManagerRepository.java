package com.asama.onlinequiz.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.Manager;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, String> {

    Optional<Manager> findByIdAndPassword(String user, String password);
}
