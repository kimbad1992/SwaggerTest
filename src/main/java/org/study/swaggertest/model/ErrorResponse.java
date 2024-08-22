package org.study.swaggertest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
    private String path;
    private int status;
    private LocalDateTime timestamp;
}
