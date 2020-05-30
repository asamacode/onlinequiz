package com.asama.onlinequiz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.asama.onlinequiz.model.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
