package com.company.sytems;

import com.company.commands.Command;
import com.company.context.CommandType;
import com.company.controller.Response;
import com.company.controller.ResponseMessage;
import com.company.entities.World;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;

import java.util.List;

public class EngageSystem implements GameSystem<Response>{
    
    private final List<CommandType> allowedCommands;
    
    public EngageSystem(List<CommandType> commandTypes) {
        allowedCommands = commandTypes;
    }

    @Override
    public Response execute(Game game, Command command) {
        World world = game.getWorld();

        if(! allowedCommands.contains(command.getCommandType())){
            return new Response(game.getState().getStateMessage());
        }
        
        Response response = null;
        if(game.getState() != GameState.Engage){
            response = new Response(ResponseMessage.NothingToFight);
            response.addResponseMessage(game.getLastDisplayedMessage());
            return response;
        }
        
        if(command.getCommandType() == CommandType.EngageInFight) {
            game.setState(GameState.Fight);
            response =  new Response(ResponseMessage.EngageStart);
        }
        else {
            response =  new Response(ResponseMessage.InfoQDiscuss);
            response.addResponseMessage(game.getLastDisplayedMessage());
        }

        return response;
        
    }
}
