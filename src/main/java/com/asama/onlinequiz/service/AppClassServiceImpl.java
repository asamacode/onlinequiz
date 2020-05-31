package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.AppClass;
import com.asama.onlinequiz.repository.AppClassRepository;

@Service
public class AppClassServiceImpl implements AppClassService {

    @Autowired
    AppClassRepository respository;
    
    @Override
    public Optional<AppClass> findById(Long id) {
        return respository.findById(id);
    }

    @Override
    public AppClass update(AppClass appClass) {
        return respository.save(appClass);
    }

    @Override
    public AppClass save(AppClass appClass) {
        return respository.save(appClass);
    }

    @Override
    public List<AppClass> findAll() {
        return (List<AppClass>) respository.findAll();
    }

    @Override
    public void delete(AppClass appClass) {
        respository.delete(appClass);
    }

}
