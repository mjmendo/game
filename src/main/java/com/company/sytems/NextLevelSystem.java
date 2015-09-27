package com.company.sytems;

import com.company.entities.game.Game;
import com.company.commands.Command;
import com.company.context.CommandType;
import com.company.controller.Response;
import com.company.controller.ResponseMessage;
import com.company.entities.game.GameState;
import com.company.entities.level.Level;

import java.util.List;

public class NextLevelSystem implements GameSystem<Response>{
    private final List<CommandType> allowedCommands;

    public NextLevelSystem(List<CommandType> commandTypes) {
        allowedCommands = commandTypes;
    }

    @Override
    public Response execute(Game game, Command command) {
        Response response = null;

        if(! allowedCommands.contains(command.getCommandType())){
            return new Response(game.getState().getStateMessage());
        }
        
        if(game.getState() != GameState.LevelCompleted) {
            response = new Response(ResponseMessage.UnknownCommand);
            response.addResponseMessage(game.getState().getStateMessage());
            
        } else if(command.getCommandType() == CommandType.Ok) {
            ResponseMessage playNextLevel = ResponseMessage.PlayNextLevel;
            playNextLevel.setMessageParams(Level.getNextLevel(game.getLevel()).getLevelName());
            response = new Response(playNextLevel);
            
        } else if(command.getCommandType() == CommandType.Cancel){
           game.setState(GameState.SaveGame);
            response = new Response(game.getState().getStateMessage());
        }
        return response;
        
    }
}
