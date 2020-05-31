package com.asama.onlinequiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.UserRole;
import com.asama.onlinequiz.repository.UserRoleRepository;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository repository;
    
    @Override
    public List<UserRole> findAll() {
        return (List<UserRole>) repository.findAll();
    }

}
