package org.study.swaggertest.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class 중간컴포넌트 {

    @Autowired
    private 하위컴포넌트 end;

    public void 중간컴포넌트_메서드() {
        log.info("===============중간 컴포넌트 호출");
        end.하위컴포넌트_메서드();
    }

    public void 중간컴포넌트_only() {
        log.info("===============중간 컴포넌트 Only");
    }
}
