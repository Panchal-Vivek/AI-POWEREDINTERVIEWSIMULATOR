package com.ai.interview.dto;

public class QuestionResponse {

    private String question;
    private int difficulty;

    public QuestionResponse(String question, int difficulty) {
        this.question = question;
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
