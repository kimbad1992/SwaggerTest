package org.study.swaggertest;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.study.swaggertest.entity.CountryEnum;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.EnumSource.Mode.INCLUDE;

@Slf4j
public class 파라미터테스트 {

    @RepeatedTest(5)
    void 반복_테스트(RepetitionInfo repetitionInfo) {
        log.info("{}만큼 반복합니다. 현재 {}회 진행중입니다.", repetitionInfo.getTotalRepetitions(), repetitionInfo.getCurrentRepetition());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void 파라미터_테스트(int n) {
        log.info("파라미터 테스트 : {}회 반복", n);
    }

//    @ParameterizedTest
//    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9}, strings = {"하나", "둘", "셋", "야!"})
//    void 파라미터_테스트(int n, String str) {
//        // @ValueSource는 파라미터 2개를 받을 수 없음
//    }

    @ParameterizedTest
    @NullSource
    void 널임(String str) {
        // 원시타입은 사용할 수 없다.
        assertNull(str);
    }

    @ParameterizedTest
    @EmptySource
    void 비어있음(Map map) {
        // 비어있는 Map 객체 {}를 반환하므로 Null이 아니다.
        // 원시타입 배열, 객체 배열, Collection 객체 등에 사용 가능하다.
        assertNull(map);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { "a", "b", "c", "d" })
    void 널이고비어있음(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }

    @ParameterizedTest
    // @EnumSource(value = CountryEnum.class)
    @EnumSource // value를 지정하지 않을 시 메서드 인자로 추론한다.
    void 이넘_테스트(CountryEnum countryEnum) {
        log.info("요거! : {}", countryEnum);
    }

    @ParameterizedTest
    @EnumSource(value = CountryEnum.class, mode = INCLUDE, names = {"CANADA", "TURKEY", "FRANCE"})
    void 이넘_모드(CountryEnum countryEnum) {
        log.info("names를 포함한 ENUM : {}", countryEnum);
    }

    @ParameterizedTest
    @MethodSource(value = "methodTestProvider")
    void 메서드_테스트(String str, int num, List<String> list) {
        log.info("문자열 : {}, 정수 : {}, 리스트 : {}", str, num, list);
    }

    static Stream<Arguments> methodTestProvider() {
        return Stream.of(
                Arguments.arguments("가", 1, Arrays.asList("A", "B")),
                Arguments.arguments("나", 2, Arrays.asList("C", "D"))
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "가, A",
            "나, B",
            "다, C",
            "라, ''",
            "ImNull, Metoo"
    },
    emptyValue = "Empty",
    nullValues = {"ImNull", "Metoo"})
    void CSV_테스트(String hangul, String alpha) {
        log.info("한글 : {}, 알파벳 : {}", hangul, alpha);
        // assertNull(hangul, alpha); // nullValue로 지정된 값만 Null로 변경됨
    }
}
