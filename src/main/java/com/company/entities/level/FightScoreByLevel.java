package com.company.entities.level;

import lombok.Getter;

@Getter
public enum FightScoreByLevel {
    
    Level_1(10, 0, 3, 1, 3);
    
    
    private final int maxScore;
    private final int minRandom;
    private final int maxRandom;
    private final int punchValue;
    private final int kickValue;
    

    FightScoreByLevel(int maxScore, int minRandom, int maxRandom,
                      int punchValue, int kickValue) {
        this.maxScore = maxScore;
        this.minRandom = minRandom;
        this.maxRandom = maxRandom;
        this.punchValue = punchValue;
        this.kickValue = kickValue;
    }
}
