package com.company.controller;

import com.company.context.CommandFactory;
import com.company.entities.game.Game;
import com.company.commands.Command;
import com.company.context.CommandType;
import com.company.context.SystemLocator;
import com.company.entities.stats.history.Record;
import com.company.sytems.GameSystem;
import lombok.Data;

@Data
public class GameController {
    private final CommandFactory commandFactory;
    private Game game;
    private final SystemLocator systemLocator;

    public GameController(SystemLocator systemLocator, CommandFactory commandFactory, Game game){
        this.game = game;
        this.systemLocator = systemLocator;
        this.commandFactory = commandFactory;
    }
    

    public Response processInput(String commandLineInput) {
        
        Response response = verifyCommand(commandLineInput);

        if(response != null)
            return response;

        GameSystem<Response> system = systemLocator.getSystemByGameState(game.getState());

        Command command = commandFactory.buildCommand(commandLineInput);
        
        response = system.execute(game, command);
        
        game.setLastDisplayedMessage(response.getMessages().get(response.getMessages().size() - 1));
        
        recordPlay(CommandType.getByKey(commandLineInput), response);
        
        return response;

    }

    private Response verifyCommand(String commandLineInput){
        
        Response response = null;
        
        if(commandLineInput == null || commandLineInput.isEmpty()){
            response = new Response(ResponseMessage.UnknownCommand);
        }

        CommandType commandType = CommandType.getByKey(commandLineInput);
        if(commandType == CommandType.Quit){
            response = new Response(ResponseMessage.QuitTheGame);
        }

        if(response != null)
            response.addResponseMessage(game.getState().getStateMessage());
        
        return response;
    }


    private void recordPlay(CommandType byKey, Response response) {
        Record record = Record.builder()
                .requestedCommand(byKey)
                .gameState(game.getState())
                .response(response)
                .build();
        game.getHistory().addRecordToHistory(record);
    }
}
