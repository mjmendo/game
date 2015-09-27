package com.company.sytems;

import com.company.entities.game.Game;
import com.company.commands.Command;

public interface GameSystem<T> {
    
    T execute(Game world, Command command);
}
