package org.study.swaggertest.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class 하위컴포넌트 {

    public void 하위컴포넌트_메서드() {
        log.info("===============하위 컴포넌트 호출");
    }

    public void 하위컴포넌트_only() {
        log.info("===============하위 컴포넌트 Only");
    }
}
