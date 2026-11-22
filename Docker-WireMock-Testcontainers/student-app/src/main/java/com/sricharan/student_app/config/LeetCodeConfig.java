package com.sricharan.student_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class LeetCodeConfig {

    @Value("${leetcode.uri}")
    String leetCodeUri;

    @Value("${leetcode.api.connect-timeout:3s}")
    Duration connectTimeout;

    @Value("${leetcode.api.read-timeout:5s}")
    Duration readTimeout;

    @Bean
    public RestClient leetCodeClient(RestClient.Builder builder) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);

        return builder
                .baseUrl(leetCodeUri)
                .requestFactory(factory)
                .build();
    }
}