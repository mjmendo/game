package com.company.sytems;

import com.company.commands.Command;
import com.company.context.CommandType;
import com.company.controller.Response;
import com.company.controller.ResponseMessage;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;
import com.company.files.FileManager;

import java.util.List;

public class SaveGameSystem implements GameSystem<Response>{
    private final List<CommandType> allowedCommands;
    private FileManager fileManager;

    public SaveGameSystem(List<CommandType> commandTypes, FileManager fileManager) {
        allowedCommands = commandTypes;
        this.fileManager = fileManager;
    }

    @Override
    public Response execute(Game game, Command command) {

        if(! allowedCommands.contains(command.getCommandType())){
            return new Response(game.getState().getStateMessage());
        }
        Response response = null;
        if(command.getCommandType() == CommandType.Ok){
            game.setState(GameState.Explore);
            ResponseMessage gameSaved = ResponseMessage.GameSaved;
            gameSaved.setMessageParams(game.getCharacter().getName(), fileManager.saveGame(game));
            response = new Response(gameSaved);
            response.addResponseMessage(game.getState().getStateMessage());
            
        } else if (command.getCommandType() == CommandType.Cancel){
            game.setState(GameState.Explore);
            response = new Response(game.getState().getStateMessage());
        }
        
        
        
        return response;
        
    }

}
