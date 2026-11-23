package com.sricharan.student_app.constant;

public class LeetcodeQuery {

     public static final String PROGRESS = """
             query userProfileUserQuestionProgressV2($userSlug: String!) {
               userProfileUserQuestionProgressV2(userSlug: $userSlug) {
                 numAcceptedQuestions {
                   count
                   difficulty
                 }
                 numFailedQuestions {
                   count
                   difficulty
                 }
                 numUntouchedQuestions {
                   count
                   difficulty
                 }
                 userSessionBeatsPercentage {
                   difficulty
                   percentage
                 }
                 totalQuestionBeatsPercentage
               }
             }
             """;
}
