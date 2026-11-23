package com.sricharan.student_app.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ExceptionResponse {

    private LocalDateTime timestamp;

    private String message;

    private String path;

    private Integer statusCode;
}
