package org.study.swaggertest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.study.swaggertest.model.UserDetail;
import org.study.swaggertest.entity.User;
import org.study.swaggertest.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDetailsService {

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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.trim().isEmpty()) {
            throw new UsernameNotFoundException("ID를 입력해주세요.");
        }

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("아이디를 잘못 입력했습니다.");
        }

        User user = optionalUser.get();
        if (!user.getEnabled()) {
            throw new UsernameNotFoundException("사용할 수 없는 계정입니다.");
        }

        // User 엔티티를 UserDetails로 변환하여 반환
        return new UserDetail(user);
    }
}
