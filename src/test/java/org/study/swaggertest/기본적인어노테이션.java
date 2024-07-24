package org.study.swaggertest;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
@DisplayName("클래스에도 DisplayName을 지정할 수 있습니다.")
public class 기본적인어노테이션 {

    @BeforeAll
    static void beforeAll() {
        // 테스트 시작 전에 실행되는 메서드 (static)
        log.info("-- 테스트 클래스 실행 전");
    }

    @AfterAll
    static void afterAll() {
        // 테스트 종료 후에 실행되는 메서드 (static)
        log.info("-- 테스트 클래스 종료");
    }

    @BeforeEach
    void beforeEach() {
        // 각 테스트 메서드 시작 전에 실행되는 메서드
        log.info("--- @BeforeEach 테스트 시작 전");
    }

    @AfterEach
    void afterEach() {
        // 각 테스트 메서드 종료 후에 실행되는 메서드
        log.info("--- @AfterEach 테스트 종료");
    }

    @Test // 테스트 메서드를 나타냄. 테스트 클래스에 필수로 작성되어야함
    void 테스트_01() {
        log.info("--- 테스트_01");
    }

    @Test // 테스트 메서드를 나타냄. 테스트 클래스에 필수로 작성되어야함
    void 테스트_02() {
        log.info("--- 테스트_02");
    }

    @Test
    @DisplayName("테스트 메서드에도 DisplayName 지정이 가능합니다.")
    void 테스트_03() {
        log.info("--- 테스트_03");
    }

    @RepeatedTest(5) // 반복적인 테스트에 사용됩니다.
    void 반복테스트(RepetitionInfo repetitionInfo) {
        log.info("--- 반복테스트 {}회 반복", repetitionInfo.getCurrentRepetition());
    }

    @Test
    @Disabled // 실행시키지 않을 (비활성화시킬) 테스트 메서드에 작성
    void 안쓰는_테스트() {
        log.info("--- 이건 안쓸래요");
    }
}
