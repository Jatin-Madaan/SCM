package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.UserRepository;
import com.scm.services.UserService;

import ch.qos.logback.classic.Logger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        User createdUser = userRepository.save(user);
        logger.info("User created with id: " + createdUser.getUserId());
        return createdUser;
    }

    @Override
    public Optional<User> getUserByUserId(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user.getUserId()));
        // update karenge existing user ko
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setAbout(user.getAbout());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setProfilePic(user.getProfilePic());
        existingUser.setEnabled(user.isEnabled());
        existingUser.setEmailVerified(user.isEmailVerified());
        existingUser.setPhoneVerified(user.isPhoneVerified());
        existingUser.setProvider(user.getProvider());
        existingUser.setProviderUserId(user.getProviderUserId());

        // save the updated user
        User updatedUser = userRepository.save(existingUser);
        logger.info("User updated with id: " + updatedUser.getUserId());
        return Optional.of(updatedUser);
    }

    @Override
    public void deleteUserByUserId(String userId) {
        userRepository.findById(userId)
            .ifPresentOrElse(user -> {
                userRepository.deleteById(userId);
                logger.info("User deleted with id: " + userId);
            }, () -> {
                throw new ResourceNotFoundException("User not found with id: " + userId);
            });
    }

    @Override
    public boolean isUserExists(String userId) {
        return userRepository.findById(userId).isPresent();
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
        

}
