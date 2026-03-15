package com.ai.interview.service;

import org.springframework.stereotype.Service;

@Service
public class ScoringService {

    public int calculateScore(int quality, int difficulty) {
        return quality * difficulty;
    }

    public String determineLevel(int score) {
        if (score >= 120) return "Advanced";
        if (score >= 60) return "Intermediate";
        return "Beginner";
    }
}
