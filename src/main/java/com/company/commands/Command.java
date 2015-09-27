package com.company.commands;

import com.company.context.CommandType;

public interface Command {

    CommandType getCommandType();

    void setCommandType(CommandType commandType);
}
