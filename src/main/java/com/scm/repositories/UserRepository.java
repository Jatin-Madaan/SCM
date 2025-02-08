package com.scm.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // add more methods if required related to services
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
