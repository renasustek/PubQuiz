package com.github.renas.PubQuiz.gameSession;

public enum GameStatus {
    WAITING("Waiting"),
    INPROGRESS("Inprogress"),
    COMPLETED("Finished");

    private final String displayName;

    GameStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}