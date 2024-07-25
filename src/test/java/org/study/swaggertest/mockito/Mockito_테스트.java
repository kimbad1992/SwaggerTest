package org.study.swaggertest.mockito;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class Mockito_테스트 {

    @Test
    void SPY_테스트() {
        List<String> originalList = new ArrayList<>();

        List<String> spyList = Mockito.spy(originalList);

        Mockito.doReturn("Mocked").when(spyList).getFirst();

        String element = spyList.getFirst(); // "Mocked" 반환
        log.info("spyList element : {}, spyList Size : {}", element, spyList.size());
    }

    @Test
    void Mock_테스트() {
        // 1. 모의 객체 생성 (Mock)
        // 리스트 클래스를 Mocking하여 List처럼 동작하는 모의 객체 생성
        List<String> mockList = Mockito.mock(List.class);

        // 2. 메서드 (Stub)
        // 모의 객체의 특정 메서드를 호출했을 때 예상 동작을 설정한다.
        Mockito.when(mockList.size()).thenReturn(5);
        log.info("Mock Size : {}", mockList.size()); // :: 5

        // 메서드 호출 검증 : Verify
        Mockito.verify(mockList).size();

        log.info("Mock Size : {}", mockList.add("우"));
        Mockito.verify(mockList).size();
        log.info("Mock : {}", mockList);
    }

}
