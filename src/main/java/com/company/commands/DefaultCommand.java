package com.company.commands;

import com.company.context.CommandType;
import lombok.Data;

@Data
public class DefaultCommand implements Command {
    
    protected CommandType commandType;
    
    public DefaultCommand(CommandType commandType){
        this.commandType = commandType;
        
    }
    
    public DefaultCommand(){}

}
