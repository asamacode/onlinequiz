package com.asama.onlinequiz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.Manager;
import com.asama.onlinequiz.repository.ManagerRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;
    
    @Override
    public Optional<Manager> findByIdAndPass(String userId, String pass) {
        return managerRepository.findByIdAndPassword(userId, pass);
    }

}
