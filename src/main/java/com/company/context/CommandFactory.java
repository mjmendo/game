package com.company.context;

import com.company.commands.Command;
import com.company.commands.InputCommand;
import com.company.exception.GameException;

public class CommandFactory {

    private final ApplicationContext ctx;

    public CommandFactory(ApplicationContext ctx){
        this.ctx = ctx;
    }
    
    public Command buildCommand(String input){

        Command commandToExecute = null;
        
        CommandType commandType = CommandType.getByKey(input);
        
        if(commandType == CommandType.Unknown){
            commandType = CommandType.InputCommand;
            commandToExecute = getCommandInstance(commandType);
            InputCommand.class.cast(commandToExecute).setInput(input);
            
        } else{
            commandToExecute = getCommandInstance(commandType);
        }

        return commandToExecute;
    }

    private Command getCommandInstance(CommandType commandType) {
        try {
            Command command = commandType.getCommandClass().newInstance();
            command.setCommandType(commandType);

            return command;
        } catch (Exception e) {
            throw new GameException("Error while creating instance of new command", e);
        }
    }


}
