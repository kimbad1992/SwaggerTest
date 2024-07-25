package org.study.swaggertest.mockito;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.study.swaggertest.entity.User;
import org.study.swaggertest.repository.UserRepository;
import org.study.swaggertest.service.UserService;

import java.util.List;

@SpringBootTest
@Slf4j
public class Mockito_InjectMocks_테스트 {

    @Spy
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    User newUser;

    @BeforeEach
    void 가입회원() {
        newUser = new User();
        newUser.setUsername("choi");
        newUser.setPassword("123456");
        newUser.setNickname("최은수");
    }

    @Test
    void InjectMocks_테스트() {
        Mockito.doAnswer(invoke -> {
            log.info("Spy::User Save Called.");
            return null;
        }).when(userRepository).save(newUser);

        userService.save(newUser); // Spy의 save() Stub가 실행

        userService.findAll().forEach(user -> log.info("InjectMocks::{}",user.toString()));

        // Repository는 Bean이기 때문에 단순 @Spy로는 실행되지 않음
        userRepository.findAll().forEach(user -> log.info("Spy::{}",user.toString()));
    }
}
