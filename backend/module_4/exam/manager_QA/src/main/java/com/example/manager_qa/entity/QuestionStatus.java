package com.example.manager_qa.entity;

public enum QuestionStatus {
    WAITING("Chờ phản hồi"),
    RESPONSE("Đã phản hồi");

    private final String displayName;

    QuestionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
