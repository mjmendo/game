package com.company.sytems;

import com.company.commands.Command;
import com.company.context.CommandType;
import com.company.controller.Response;
import com.company.controller.ResponseMessage;
import com.company.entities.Fight;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

public class FightSystem implements GameSystem<Response> {
    
    private final List<CommandType> allowedCommands;
    
    public FightSystem(List<CommandType> commandTypes) {
        allowedCommands = commandTypes;
    }

    @Override
    public Response execute(Game game, Command command) {
        if(game.getState() != GameState.Fight){
            return new Response(ResponseMessage.NothingToFight);
        }

        if(! allowedCommands.contains(command.getCommandType())){
            return new Response(game.getState().getStateMessage());
        }

        Response response = null;
        
        //-- Who hits first??
        if(RandomUtils.nextInt(0, 2) == 0){
            response = processGeekHit(game);
            response = response != null 
                    ? response 
                    : processZombieHit(command, game);
        } else {
            response = processZombieHit(command, game);
            Response gameOver = processGeekHit(game);
            response = gameOver != null 
                    ? gameOver 
                    : response;
        }
        
        if(game.getState() == GameState.Fight)
            response.addResponseMessage(processScoreMessage(game));
        
        return response;
    }
    
    private Response processZombieHit(Command command, Game game){

        Response response = null;

        int zombieScore = game.getWorld().getFight().getZombieScore();
        
        if(command.getCommandType() == CommandType.FightWithKick){
            zombieScore += game.getLevel().getFightScore().getKickValue();
            game.getWorld().getFight().setZombieScore(zombieScore);
            response = new Response(ResponseMessage.KickSound);
            
        } else if (command.getCommandType() == CommandType.FightWithPunch){
            zombieScore += game.getLevel().getFightScore().getPunchValue();
            game.getWorld().getFight().setZombieScore(zombieScore);
            response = new Response(ResponseMessage.PunchSound);
        }

        if(zombieScore >= game.getLevel().getFightScore().getMaxScore()){
            game.setState(GameState.Reward);
            
            //-- Geek defeated! Delete its position value
            game.getWorld().getMaze().deleteItemAt(game.getWorld().getFight().getGeekPosition());
            
            //-- Move zombie to defeated geek position
            game.getWorld().getZombie().setPosition(game.getWorld().getFight().getGeekPosition());
            response = new Response(ResponseMessage.ZombieWonFight);
        }
        
        return response;
    }
    
    private Response processGeekHit(Game game){
        int geekScore = game.getWorld().getFight().getGeekScore();
        game.getWorld().getFight().setGeekScore( geekScore +
                RandomUtils.nextInt(game.getLevel().getFightScore().getMinRandom(),
                        game.getLevel().getFightScore().getMaxRandom() + 1));

        if(geekScore >= game.getLevel().getFightScore().getMaxScore()){
            game.setState(GameState.GameOver);
            return new Response(ResponseMessage.GeekWonFight);
        }
        
        return null;
    }
    
    private ResponseMessage processScoreMessage(Game game){
        Fight fight = game.getWorld().getFight();
        
        String geekScore = fight.getGeekScore() + "";
        String zombieScrore = fight.getZombieScore()  + "";
        String maxFightScore = game.getLevel().getFightScore().getMaxScore()  + "";
        
        ResponseMessage partialScore = ResponseMessage.PartialScore;
        partialScore.setMessageParams(geekScore, zombieScrore, maxFightScore);
        
        return partialScore;
    }
}
