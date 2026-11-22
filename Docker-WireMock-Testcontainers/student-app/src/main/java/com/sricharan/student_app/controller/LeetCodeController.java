package com.sricharan.student_app.controller;

import com.sricharan.student_app.dto.LeetCodeProgressResponseDTO;
import com.sricharan.student_app.service.LeetCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leetcode")
@RequiredArgsConstructor
public class LeetCodeController {

    private final LeetCodeService leetCodeService;

    @GetMapping("/{username}/progress")
    public ResponseEntity<LeetCodeProgressResponseDTO> getProgress(
            @PathVariable String username) {

        return ResponseEntity.ok(
                leetCodeService.getProgress(username)
        );
    }
}