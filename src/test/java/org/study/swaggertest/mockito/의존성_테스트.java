package org.study.swaggertest.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.study.swaggertest.study.상위컴포넌트;
import org.study.swaggertest.study.중간컴포넌트;
import org.study.swaggertest.study.하위컴포넌트;

@SpringBootTest
public class 의존성_테스트 {

    @MockBean
    중간컴포넌트 mid;

    @SpyBean
    하위컴포넌트 end;

    @SpyBean
    상위컴포넌트 top;

    @Test
    void 컴포넌트테스트() {
        top.상위컴포넌트_메서드();
        mid.중간컴포넌트_only();
        end.하위컴포넌트_only();
    }
}
