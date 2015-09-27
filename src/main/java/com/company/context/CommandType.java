package com.company.context;

import com.company.commands.Command;
import com.company.commands.DefaultCommand;
import com.company.commands.InputCommand;
import lombok.Getter;

@Getter
public enum CommandType {
    Unknown("Unknown", null),
    Quit("Quit", null),
    MoveNorth("N", DefaultCommand.class),
    MoveSouth("S", DefaultCommand.class ),
    MoveEast("E", DefaultCommand.class),
    MoveWest("W", DefaultCommand.class),
    EngageInFight("F", DefaultCommand.class),
    EngageInTalk("Q", DefaultCommand.class),
    FightWithKick("K", DefaultCommand.class),
    FightWithPunch("P", DefaultCommand.class),
    EatBrain("B", DefaultCommand.class),
    KeepExploring("X", DefaultCommand.class),
    Ok("Y", DefaultCommand.class),
    Cancel("C", DefaultCommand.class),
    Option_1("1", DefaultCommand.class),
    Option_2("2", DefaultCommand.class ),
    InputCommand("", InputCommand.class),
    Save("save", DefaultCommand.class);


    private final String key;
    private final Class<? extends Command> clazz;

    CommandType(String key, Class<? extends Command> clazz) {
        this.key = key;
        this.clazz = clazz;
    }


    public static CommandType getByKey(String userInput) {
        for (CommandType commandType : values()){
            if( commandType.key.equalsIgnoreCase(userInput)){
                return commandType;
            }
        }
        return Unknown;
    }
    
    public Class<? extends Command> getCommandClass(){
        return clazz;
    }
}
