package com.company.entities.stats.history;

import com.company.context.CommandType;
import com.company.controller.Response;
import com.company.entities.game.GameState;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Record implements Serializable {
    
    private final CommandType requestedCommand;
    private final GameState gameState;
    private final Response response;
    
    
}
