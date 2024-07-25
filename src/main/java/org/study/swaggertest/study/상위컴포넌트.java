package org.study.swaggertest.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class 상위컴포넌트 {

    @Autowired
    private 중간컴포넌트 middle;

    public void 상위컴포넌트_메서드() {
        log.info("===============상위 컴포넌트 호출");
        middle.중간컴포넌트_메서드();
    }

    public void 상위컴포넌트_only() {
        log.info("===============상위 컴포넌트 Only");
    }
}
