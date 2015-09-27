package com.company.entities.game;

import com.company.controller.ResponseMessage;
import lombok.Getter;

@Getter
public enum GameState {
    MainMenu("MainMenu", ResponseMessage.MainMenu),
    Explore("Explore", ResponseMessage.Explore),
    Fight("Fight", ResponseMessage.EngageStart),
    InfoQ("InfoQ", ResponseMessage.InfoQDiscuss),
    Reward("Reward", ResponseMessage.ZombieWonFight),
    Engage("Engage", ResponseMessage.EngageQuestion),
    GameOver("GameOver", ResponseMessage.GeekWonFight),
    LevelCompleted("LevelCompleted", ResponseMessage.LevelCompleted),
    SaveGame("SaveGame", ResponseMessage.SaveGameQuestion),
    CreateChar("CreateChar", ResponseMessage.CreateCharQuestion ), 
    LoadSavedGame("LoadSavedGame", ResponseMessage.LoadSavedGame);


    private String stateName;
    private ResponseMessage stateMessage;

    GameState(String state, ResponseMessage message){
        stateName = state;
        stateMessage = message;
    }
}
