package com.company.sytems;

import com.company.commands.Command;
import com.company.components.Position;
import com.company.context.CommandType;
import com.company.controller.Response;
import com.company.controller.ResponseMessage;
import com.company.entities.Fight;
import com.company.entities.World;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;

import java.util.List;

public class MovementSystem implements GameSystem<Response>{
    
    private final List<CommandType> allowedCommands;
    
    public MovementSystem(List<CommandType> allowedCommands){
        this.allowedCommands = allowedCommands;
    }

    public Response execute(Game game, Command command) {

        if(game.getState() != GameState.Explore) {
            Response response = new Response(ResponseMessage.CannotMoveNow);
            response.addResponseMessage(game.getState().getStateMessage());
            return response;
        }
        
        if(! allowedCommands.contains(command.getCommandType())){
            Response response = new Response(ResponseMessage.ShouldBeExploring);
            response.addResponseMessage(game.getState().getStateMessage());
            return response;
        }
        
        if(command.getCommandType() == CommandType.Save){
            game.setState(GameState.SaveGame);
            return new Response(game.getState().getStateMessage());
        }
        
        return processMovement(game, command);
    }

    private Response processMovement(Game game, Command command) {
        Position currentPosition = game.getWorld().getZombie().getPosition();
        World world = game.getWorld();
        
        int xInc = 0;
        int yInc = 0;

        switch (command.getCommandType()){
            case MoveNorth: xInc -= 1; break;
            case MoveSouth: xInc += 1; break;
            case MoveEast: yInc += 1; break;
            case MoveWest: yInc -= 1; break;
        }

        Response response = new Response();
        Position nextPos = new Position(currentPosition.getX() + xInc, currentPosition.getY() + yInc);

        if(nextPos == null || world.getMaze().getItemAt(nextPos) == "W"){
            response.addResponseMessage(ResponseMessage.Wall);
        } else if( world.getMaze().getItemAt(nextPos) == " ") {
            world.getZombie().setPosition(nextPos);
            response.addResponseMessage(ResponseMessage.Good);
        } else if(world.getMaze().getItemAt(nextPos) == "X"){
            game.setState(GameState.LevelCompleted);
            ResponseMessage stateMessage = game.getState().getStateMessage();
            stateMessage.setMessageParams(ResponseMessage.Level2.getMessageKey());
            response.addResponseMessage(stateMessage);
        } else {
            response.addResponseMessage(ResponseMessage.EngageQuestion);
            world.setFight(new Fight());
            world.getFight().setGeekPosition(nextPos);
            game.setState(GameState.Engage);
        }

        return response;
    }
}
