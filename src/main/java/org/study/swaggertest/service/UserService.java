package org.study.swaggertest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.swaggertest.entity.User;
import org.study.swaggertest.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        log.info("Find all users");
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        log.info("Find user by id: {}", id);
        return userRepository.findById(id);
    }

    public User save(User user) {
        log.info("Save user: {}", user);
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        log.info("Delete user by id: {}", id);
        userRepository.deleteById(id);
    }

    public Optional<String> login(String username, String password) {
        log.info("Login user: {}", username);
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        return user.map(User::getNickname);
    }
}
