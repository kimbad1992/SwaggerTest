package org.study.swaggertest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

@Slf4j
public class 태깅테스트 {

    @Test
    @Tag("홀수")
    void 테스트01() {
        log.info("01번 테스트");
    }

    @Test
    @Tag("짝수")
    void 테스트02() {
        log.info("02번 테스트");
    }

    @Test
    @Tag("홀수")
    void 테스트03() {
        log.info("03번 테스트");
    }

    @Test
    @Tag("짝수")
    void 테스트04() {
        log.info("04번 테스트");
    }
}
