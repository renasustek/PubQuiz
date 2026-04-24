package com.github.renas.PubQuiz.quiz;

public enum Results {
    WINNER("Winner", 1),
    LOSER("Loser", 0);

    private final String displayName;
    private final int score;
    Results(String displayName, int score) {
        this.displayName = displayName;
        this.score = score;
    }

    public String getDisplayName() {
        return displayName;
    }
    public int getScore(){
        return score;
    }

}
