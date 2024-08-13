package org.study.swaggertest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JWTToken {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}