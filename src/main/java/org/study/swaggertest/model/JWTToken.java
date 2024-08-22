package org.study.swaggertest.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class JWTToken {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}