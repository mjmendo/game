package com.company.sytems;

import com.company.commands.Command;
import com.company.context.CommandType;
import com.company.controller.Response;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;

import java.util.List;

public class MainMenuSystem implements GameSystem<Response>{
    private final List<CommandType> allowedCommands;

    public MainMenuSystem(List<CommandType> commandTypes) {
        allowedCommands = commandTypes;
    }

    @Override
    public Response execute(Game game, Command command) {

        if(! allowedCommands.contains(command.getCommandType())){
            return new Response(game.getState().getStateMessage());
        }
        
        if(command.getCommandType() == CommandType.Option_1){
            game.setState(GameState.CreateChar);
        } else if (command.getCommandType() == CommandType.Option_2){
            game.setState(GameState.LoadSavedGame);
        }
        
        Response response = new Response(game.getState().getStateMessage());
        
        return response;
        
    }

}
