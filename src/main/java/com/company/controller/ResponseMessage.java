package com.company.controller;

import lombok.Getter;

@Getter
public enum ResponseMessage {
    
    //-- Navigation
    MainMenu(0, "menu"),
    CreateCharQuestion(1, "createCharQuestion" ),
    LoadSavedGame(2,"loadSavedGames" ),
    PickANameForChar(3,"pickNameForChar" ),
    CharNameCreated(4,"charNameCreated" ),
    EmptyInput(5, "emptyInput"),
    GameSaved(6, "gameSaved" ),

    //-- Basics
    QuitTheGame(100, "goodbye"),
    Good(101, "good"),
    Wall(102, "wall"),
    EngageQuestion(103, "engageQuestion"),
    NothingToFight(104,"nothingToFight"),
    EngageStart(105, "engageStart"),
    InfoQDiscuss(106, "infoQDiscuss"),
    GeekWonFight(107, "geekWon"),
    ZombieWonFight(108, "zombieWon"),
    KickSound(109, "kickSound"),
    PunchSound(110, "punchSound"),
    EatBrain(111, "eatBrain"),
    Explore(112, "explore"),
    NoBrainToEat(113, "noBrain"),
    PartialScore(114, "partialScore"),
    LevelCompleted(115, "levelCompleted"),
    PlayNextLevel(116, "playNextLevel"),
    SaveGameQuestion(117, "saveGameQuestion" ),

    //-- Levels
    Level2(200, "level2"),

    //-- Illegal states
    CannotMoveNow(300, "cannotMoveNow"),
    ShouldBeExploring(301, "shouldBeExploring"),


    //-- Errors
    UnknownCommand(400, "unknownCommand"),
    ;


    
    private final int code;
    private final String messageKey;
    
    private String[] messageParams;

    ResponseMessage(int code, String messageKey) {
        this.code = code;
        this.messageKey = messageKey;
    }
    
    public void setMessageParams(String... params){
        messageParams = params;
        
    }

    public boolean hasParams() {
        return messageParams != null;
    }
}
