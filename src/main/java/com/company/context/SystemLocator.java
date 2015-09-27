package com.company.context;

import com.company.controller.Response;
import com.company.entities.game.GameState;
import com.company.exception.GameException;
import com.company.sytems.GameSystem;

import java.util.EnumMap;
import java.util.Map;

public class SystemLocator {
    
    private final Map<GameState, GameSystem<Response>> availableSystemsByState;
    private final Map<CommandType, GameSystem<Response>> availableSystems;
    
    public SystemLocator(ApplicationContext ctx){
        availableSystems = new EnumMap<>(CommandType.class);
        availableSystemsByState = new EnumMap(GameState.class);
        initMapping(ctx);
    }

    public GameSystem<Response> getSystemByCommand(CommandType commandType){
        return availableSystems.get(commandType);
    }
    
    public GameSystem<Response> getSystemByGameState(GameState gameState){
        GameSystem<Response> responseGameSystem = availableSystemsByState.get(gameState);
        if(responseGameSystem == null){
            throw new GameException("No system has been found for state=" + gameState.getStateName());
        }
        return responseGameSystem;
        
    }
    
    private void initMapping(ApplicationContext ctx){
        availableSystemsByState.put(GameState.Explore, ctx.movementSystem);
        availableSystemsByState.put(GameState.Fight, ctx.fightSystem);
        availableSystemsByState.put(GameState.Engage, ctx.engageSystem);
        availableSystemsByState.put(GameState.InfoQ, ctx.engageSystem);
        availableSystemsByState.put(GameState.LevelCompleted, ctx.nextLevelSystem);
        availableSystemsByState.put(GameState.Reward, ctx.rewardSystem);
        availableSystemsByState.put(GameState.MainMenu, ctx.mainMenuSystem);
        availableSystemsByState.put(GameState.CreateChar, ctx.createCharSystem);
        availableSystemsByState.put(GameState.SaveGame, ctx.saveGameSystem);

        
    }
}
