package com.company.sytems;

import com.company.commands.Command;
import com.company.context.CommandType;
import com.company.controller.Response;
import com.company.controller.ResponseMessage;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;

import java.util.List;

public class RewardSystem implements GameSystem<Response>{
    private final List<CommandType> allowedCommands;

    public RewardSystem(List<CommandType> commandTypes) {
        allowedCommands = commandTypes;
    }

    @Override
    public Response execute(Game game, Command command) {
        Response response = null;

        if( ! isValidGameState(game)){
            response = new Response(ResponseMessage.NoBrainToEat);
            response.addResponseMessage(game.getState().getStateMessage());
            return response;
        }

        if(! allowedCommands.contains(command.getCommandType())){
            return new Response(game.getState().getStateMessage());
        }
        
        if(command.getCommandType() == CommandType.EatBrain){
            response = new Response(ResponseMessage.EatBrain);
        } else {
            response = new Response(ResponseMessage.Explore);
        }
        
        response.addResponseMessage(ResponseMessage.Explore);
        game.setState(GameState.Explore);
        
        return response;
        
    }

    private boolean isValidGameState(Game game) {
        if(game.getState() == GameState.Reward)
            return Boolean.TRUE;
        
        return Boolean.FALSE;
        
    }
}
