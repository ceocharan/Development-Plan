package com.sricharan.student_app.dto;

import java.util.List;

public record UserQuestionProgressDTO(
        List<QuestionCountDTO> numAcceptedQuestions,
        List<QuestionCountDTO> numFailedQuestions,
        List<QuestionCountDTO> numUntouchedQuestions,
        List<DifficultyPercentageDTO> userSessionBeatsPercentage,
        double totalQuestionBeatsPercentage
) {}

