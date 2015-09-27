package com.company.entities.game;

import com.company.controller.ResponseMessage;
import com.company.entities.CustomCharacter;
import com.company.entities.World;
import com.company.entities.level.Level;
import com.company.entities.stats.history.GameHistory;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
public class Game implements Serializable{
    
    private final World world;
    private final GameHistory history;
    private Level level;
    private CustomCharacter character;
    
    @Setter
    private ResponseMessage lastDisplayedMessage;
    
    @Setter
    private GameState state;
    
    public Game(World world, GameHistory history, GameState state, Level level, CustomCharacter character){
        this.world = world;
        this.history = history;
        this.state = state;
        this.level = level;
        this.character = character;
    }

}
