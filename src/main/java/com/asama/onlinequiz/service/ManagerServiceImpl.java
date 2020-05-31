package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.Manager;
import com.asama.onlinequiz.repository.ManagerRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository repository;
    
    @Override
    public Optional<Manager> findByIdAndPass(String userId, String pass) {
        return repository.findByIdAndPassword(userId, pass);
    }
    
    @Override
    public Optional<Manager> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Manager update(Manager manager) {
        return repository.save(manager);
    }

    @Override
    public Manager save(Manager manager) {
        return repository.save(manager);
    }

    @Override
    public List<Manager> findAll() {
        return (List<Manager>) repository.findAll();
    }

    @Override
    public void delete(Manager manager) {
        repository.delete(manager);
    }

}
