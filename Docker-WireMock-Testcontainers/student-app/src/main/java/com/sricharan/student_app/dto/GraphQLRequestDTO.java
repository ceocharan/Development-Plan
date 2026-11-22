package com.sricharan.student_app.dto;

import java.util.Map;

public record GraphQLRequestDTO(
        String query,
        Map<String, Object> variables,
        String operationName
) {}
