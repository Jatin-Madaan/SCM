package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.User;

public interface UserService {
    User saveUser(User user);

    Optional<User> getUserByUserId(String userId);

    Optional<User> updateUser(User user);

    void deleteUserByUserId(String userId);

    boolean isUserExists(String userId);

    boolean isUserExistsByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    List<User> findAllUsers();
}
