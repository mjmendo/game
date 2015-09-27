package com.company.commands;

import com.company.context.CommandType;
import lombok.Data;

@Data
public class InputCommand extends DefaultCommand {
    private String input;
    
    public InputCommand(CommandType commandType, String input){
        this.commandType = commandType;
        this.input = input;
    }
    
    public InputCommand(){}
}
