package com.sricharan.student_app.service;

import com.sricharan.student_app.dto.GraphQLRequestDTO;
import com.sricharan.student_app.dto.LeetCodeProgressResponseDTO;
import com.sricharan.student_app.constant.LeetcodeQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.io.UncheckedIOException;
import java.net.SocketTimeoutException;
import java.util.Map;

@Service
@AllArgsConstructor
public class LeetCodeService {

    private final RestClient restClient;

    public LeetCodeProgressResponseDTO getProgress(String username) {
        GraphQLRequestDTO request = new GraphQLRequestDTO(
                LeetcodeQuery.PROGRESS,
                Map.of("userSlug", username),
                "userProfileUserQuestionProgressV2"
        );

        try {
            return restClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .body(LeetCodeProgressResponseDTO.class);
        }
        catch (RestClientException ex) {
            if (ex.getMostSpecificCause() instanceof SocketTimeoutException) {
                throw new RuntimeException("LeetCode API timed out for user: " + username, ex);
            }
            throw ex;
        }
        catch (UncheckedIOException e) {
            throw new RuntimeException("LeetCode API timed out for user: " + username, e);
        }

    }
}
