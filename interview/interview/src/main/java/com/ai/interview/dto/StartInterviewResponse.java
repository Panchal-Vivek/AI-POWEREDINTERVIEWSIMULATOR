package com.ai.interview.dto;

import java.util.UUID;

public class StartInterviewResponse {

    private UUID sessionId;

    public StartInterviewResponse(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }
}
