package com.company.entities.level;

import lombok.Getter;

@Getter
public enum Level {
    
    Level_1("level1Name", FightScoreByLevel.Level_1),
    Level_2("level2Name", FightScoreByLevel.Level_1),
    NoMoreLevels("maxLevelReached", null);
    ;
    
    private final FightScoreByLevel fightScore;
    private final String levelName;

    Level(String levelName, FightScoreByLevel level){
        fightScore = level;
        this.levelName = levelName;
    }
    
    public static Level getNextLevel(Level previousLevel){
        if(Level.values().length < previousLevel.ordinal() + 1);
        return Level.values()[previousLevel.ordinal() + 1];
    }
}
