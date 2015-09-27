package com.company.sytems;

import com.company.commands.Command;
import com.company.commands.InputCommand;
import com.company.context.CommandType;
import com.company.controller.Response;
import com.company.controller.ResponseMessage;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;

import java.util.List;

public class CreateCharSystem implements GameSystem<Response>{
    private final List<CommandType> allowedCommands;

    public CreateCharSystem(List<CommandType> commandTypes) {
        allowedCommands = commandTypes;
    }

    @Override
    public Response execute(Game game, Command command) {
        Response response = null;
        if(! allowedCommands.contains(command.getCommandType())){
            return new Response(game.getState().getStateMessage());
        }
        
        if(command.getCommandType() == CommandType.Ok){
            response = new Response(ResponseMessage.PickANameForChar);
            
        } else if (command.getCommandType() == CommandType.Cancel){
            game.setState(GameState.Explore);
            response = new Response(game.getState().getStateMessage());
            
        } else if (command.getCommandType() == CommandType.InputCommand){
            InputCommand com = InputCommand.class.cast(command);
            
            if(com.getInput() == null || com.getInput().isEmpty()){
                response = new Response(ResponseMessage.EmptyInput);
                response.addResponseMessage(game.getState().getStateMessage());
                
            } else {
                game.getCharacter().setName(com.getInput());
                game.setState(GameState.Explore);
                ResponseMessage charNameCreated = ResponseMessage.CharNameCreated;
                charNameCreated.setMessageParams(com.getInput());
                response = new Response(charNameCreated);
                response.addResponseMessage(game.getState().getStateMessage());
            }
        }

        return response;
        
    }

}
