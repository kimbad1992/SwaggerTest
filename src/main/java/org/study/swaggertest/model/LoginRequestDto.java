package org.study.swaggertest.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "로그인 요청용 DTO", description = "로그인 요청시 사용되는 유저 ID/비밀번호 필드를 가진 DTO입니다.")
public record LoginRequestDto(
        @Schema(description = "유저 ID", example = "user") String username,
        @Schema(description = "비밀번호", example = "pass") String password
) {}
