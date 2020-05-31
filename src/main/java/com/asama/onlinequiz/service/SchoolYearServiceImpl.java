package com.asama.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asama.onlinequiz.model.SchoolYear;
import com.asama.onlinequiz.repository.SchoolYearRepository;

@Service
public class SchoolYearServiceImpl implements SchoolYearService {

    @Autowired
    SchoolYearRepository schoolYearRepository;

    @Override
    public Optional<SchoolYear> findById(Long id) {
        return schoolYearRepository.findById(id);
    }

    @Override
    public SchoolYear update(SchoolYear schoolYear) {
        return schoolYearRepository.save(schoolYear);
    }

    @Override
    public SchoolYear save(SchoolYear schoolYear) {
        return schoolYearRepository.save(schoolYear);
    }

    @Override
    public List<SchoolYear> findAll() {
        return (List<SchoolYear>) schoolYearRepository.findAll();
    }

    @Override
    public void delete(SchoolYear schoolYear) {
        schoolYearRepository.delete(schoolYear);
    }

}
