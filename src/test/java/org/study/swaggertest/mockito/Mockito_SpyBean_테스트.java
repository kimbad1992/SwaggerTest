package org.study.swaggertest.mockito;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.study.swaggertest.entity.User;
import org.study.swaggertest.service.UserService;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class Mockito_SpyBean_테스트 {

    @SpyBean
    private UserService userService;

    Optional<User> newUser;

    @BeforeEach
    void 가입회원() {
        User user = new User();
        user.setUsername("choi");
        user.setPassword("123456");
        user.setNickname("최은수");
        newUser = Optional.of(user);
    }

    @Test
    void 서비스_레이어_테스트() {

        // Spy 객체의 응답을 Mocking할 수 있음
        Mockito.doReturn(newUser).when(userService).findById(1L);

        // Mocked 메서드 호출
        userService.findById(1L)
                .ifPresentOrElse(user -> log.info("foundUser : {}", user),
                        () -> log.info("not foundUser : {}", newUser));

        // 실제 서비스를 호출할 수도 있음
        userService.save(newUser.get());

        userService.findAll().forEach(user -> log.info(user.toString()));
    }
}
