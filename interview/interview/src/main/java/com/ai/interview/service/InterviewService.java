package com.ai.interview.service;

import com.ai.interview.dto.SubmitAnswerRequest;
import com.ai.interview.dto.SubmitAnswerResponse;
import com.ai.interview.model.InterviewSession;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InterviewService {

    private final ScoringService scoringService;
    private final Map<UUID, InterviewSession> sessions = new HashMap<>();

    private final List<String> QUESTION_BANK = List.of(
            "What is Java and why is it platform independent?",
            "Explain OOP concepts in Java.",
            "Difference between JVM, JRE and JDK?",
            "What is Spring Boot?",
            "Explain REST API."
    );

    public InterviewService(ScoringService scoringService) {
        this.scoringService = scoringService;
    }

    public InterviewSession createSession() {
        InterviewSession session = new InterviewSession();
        session.setSessionId(UUID.randomUUID());
        session.setCurrentQuestionIndex(0);
        session.setTotalScore(0);
        session.setCompleted(false);
        session.setQuestions(QUESTION_BANK);

        sessions.put(session.getSessionId(), session);
        return session;
    }

    public SubmitAnswerResponse submitAnswer(SubmitAnswerRequest request) {

        InterviewSession session = sessions.get(request.getSessionId());

        int quality = evaluateAnswer(request.getAnswer());
        int score = scoringService.calculateScore(quality, request.getDifficulty());

        session.setTotalScore(session.getTotalScore() + score);
        session.setCurrentQuestionIndex(session.getCurrentQuestionIndex() + 1);

        SubmitAnswerResponse response = new SubmitAnswerResponse();
        response.setScore(session.getTotalScore());

        if (session.getCurrentQuestionIndex() >= session.getQuestions().size()) {
            session.setCompleted(true);
            session.setLevel(scoringService.determineLevel(session.getTotalScore()));
            response.setCompleted(true);
            response.setLevel(session.getLevel());
        } else {
            response.setCompleted(false);
            response.setNextQuestion(
                    session.getQuestions().get(session.getCurrentQuestionIndex())
            );
        }

        return response;
    }

    private int evaluateAnswer(String answer) {
        if (answer == null) return 0;
        if (answer.length() > 100) return 10;
        if (answer.length() > 50) return 7;
        return 5;
    }
}
