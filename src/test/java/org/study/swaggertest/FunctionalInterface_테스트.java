package org.study.swaggertest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.study.swaggertest.entity.User;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class FunctionalInterface_테스트 {

    @FunctionalInterface
    interface UserInterface<User> {
        boolean isLee(User user);
    }

    User newUser;

    @BeforeEach
    void 유저() {
        newUser = new User();
        newUser.setUsername("choi");
        newUser.setPassword("123456");
        newUser.setNickname("최은수");
    }

    @Test
    void 함수형인터페이스() {
        UserInterface<User> userInterface = user -> user.getNickname().contains("Lee");

        assertTrue(userInterface.isLee(newUser));
    }
}
