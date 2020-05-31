package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import com.asama.onlinequiz.model.Manager;

public interface ManagerService {

    Optional<Manager> findByIdAndPass(String userId, String pass);
    
    Optional<Manager> findById(String id);

    Manager update(Manager manager);

    Manager save(Manager manager);

    List<Manager> findAll();

    void delete(Manager manager);
}
