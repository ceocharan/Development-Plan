package com.sricharan.student_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ExceptionResponse> runtimeException(RuntimeException runtimeException, WebRequest webRequest) {
//        ExceptionResponse exceptionResponse = ExceptionResponse.builder().message(runtimeException.getMessage())
//                                                                         .path(webRequest.getContextPath())
//                                                                         .statusCode(404)
//                                                                         .timestamp(LocalDateTime.now(ZoneId.of("IST")))
//                                                                         .build();
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
//    }
}
