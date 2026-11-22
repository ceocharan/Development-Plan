package com.sricharan.student_app;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.sricharan.student_app.repository.StudentRepository;
import com.sricharan.student_app.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.net.SocketTimeoutException;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentServiceTests {

    @RegisterExtension
    static WireMockExtension wireMock = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    @Autowired
    private StudentService studentService; // Real bean injected by Spring

    @MockitoBean
    private StudentRepository studentRepository; // Mocked so no DB is required

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("leetcode.uri", wireMock::baseUrl); // Changed from "leetcode.api.url"
        registry.add("leetcode.api.connect-timeout", () -> "500ms");
        registry.add("leetcode.api.read-timeout", () -> "500ms");
    }

    @Test
    void getProgress_whenLeetCodeTimesOut_throwsRuntimeException() {
        // Arrange: Mock repository to return username for studentId = 1
        when(studentRepository.findNameById(1)).thenReturn(Optional.of("sricharan"));

        // Arrange: Simulate a 2-second delay from LeetCode (exceeds 500ms read timeout)
        wireMock.stubFor(post(urlEqualTo("/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withFixedDelay(2000)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                        {
                                            "data": {
                                                "userProfileUserQuestionProgressV2": {
                                                    "numAcceptedQuestions": [
                                                        {
                                                            "count": 639,
                                                            "difficulty": "EASY"
                                                        },
                                                        {
                                                            "count": 692,
                                                            "difficulty": "MEDIUM"
                                                        },
                                                        {
                                                            "count": 19,
                                                            "difficulty": "HARD"
                                                        }
                                                    ],
                                                    "numFailedQuestions": [
                                                        {
                                                            "count": 12,
                                                            "difficulty": "EASY"
                                                        },
                                                        {
                                                            "count": 43,
                                                            "difficulty": "MEDIUM"
                                                        },
                                                        {
                                                            "count": 4,
                                                            "difficulty": "HARD"
                                                        }
                                                    ],
                                                    "numUntouchedQuestions": [
                                                        {
                                                            "count": 326,
                                                            "difficulty": "EASY"
                                                        },
                                                        {
                                                            "count": 1415,
                                                            "difficulty": "MEDIUM"
                                                        },
                                                        {
                                                            "count": 969,
                                                            "difficulty": "HARD"
                                                        }
                                                    ],
                                                    "userSessionBeatsPercentage": [
                                                        {
                                                            "difficulty": "EASY",
                                                            "percentage": 99.95
                                                        },
                                                        {
                                                            "difficulty": "MEDIUM",
                                                            "percentage": 99.69
                                                        },
                                                        {
                                                            "difficulty": "HARD",
                                                            "percentage": 76.33
                                                        }
                                                    ],
                                                    "totalQuestionBeatsPercentage": 99.9
                                                }
                                            }
                                        }
                                """)));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                studentService.getProgress(1)
        );

        assertTrue(exception.getMessage().contains("LeetCode API timed out for user: sricharan"));
        assertInstanceOf(SocketTimeoutException.class, exception.getCause().getCause());
    }
}