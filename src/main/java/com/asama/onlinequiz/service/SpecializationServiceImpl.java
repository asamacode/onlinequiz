package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.Specialization;
import com.asama.onlinequiz.repository.SpecializationRepository;

@Service
public class SpecializationServiceImpl implements SpecializationService {
    @Autowired
    SpecializationRepository respository;
    
    @Override
    public Optional<Specialization> findById(Long id) {
        return respository.findById(id);
    }

    @Override
    public Specialization update(Specialization specialization) {
        return respository.save(specialization);
    }

    @Override
    public Specialization save(Specialization specialization) {
        return respository.save(specialization);
    }

    @Override
    public List<Specialization> findAll() {
        return (List<Specialization>) respository.findAll();
    }

    @Override
    public void delete(Specialization specialization) {
        respository.delete(specialization);
    }

}
