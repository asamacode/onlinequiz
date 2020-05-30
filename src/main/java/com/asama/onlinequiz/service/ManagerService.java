package com.asama.onlinequiz.service;

import java.util.Optional;

import com.asama.onlinequiz.model.Manager;

public interface ManagerService {

    Optional<Manager> findByIdAndPass(String userId, String pass);
}
