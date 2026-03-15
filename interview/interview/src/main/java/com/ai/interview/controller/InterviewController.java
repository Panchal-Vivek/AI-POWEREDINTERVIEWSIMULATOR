package com.ai.interview.controller;

import com.ai.interview.dto.SubmitAnswerRequest;
import com.ai.interview.dto.SubmitAnswerResponse;
import com.ai.interview.model.InterviewSession;
import com.ai.interview.service.InterviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview")
@CrossOrigin
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/start")
    public InterviewSession startInterview() {
        return interviewService.createSession();
    }

    @PostMapping("/submit")
    public SubmitAnswerResponse submitAnswer(
            @RequestBody SubmitAnswerRequest request) {
        return interviewService.submitAnswer(request);
    }
}
