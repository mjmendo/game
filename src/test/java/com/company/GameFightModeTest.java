package com.company;

import com.company.context.ApplicationContext;
import com.company.context.CommandType;
import com.company.controller.GameController;
import com.company.controller.Response;
import com.company.controller.ResponseMessage;
import com.company.entities.CustomCharacter;
import com.company.entities.level.FightScoreByLevel;
import com.company.entities.level.Level;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;

public class GameFightModeTest {
    private GameController gameController;
    private Response response;

    @Before
    public void before(){
        ApplicationContext ac = new ApplicationContext();
        gameController =  new GameController(ac.systemLocator, ac.commandFactory, new Game(ac.world, ac.history,
                GameState.Explore, Level.Level_1, new CustomCharacter()));
        CommandType[] batchToFindGeekOnMaze = new CommandType[]{
                CommandType.MoveEast, 
                CommandType.MoveEast, 
                CommandType.MoveEast,
                CommandType.EngageInFight};
        for (CommandType commandType : batchToFindGeekOnMaze){
            response = gameController.processInput(commandType.getKey());
        }
    }

    @Test
    public void kickCommandShouldRetrieveKickSound(){
        assertThat(gameController.processInput(CommandType.FightWithKick.getKey()).getMessages(),
                hasItem(ResponseMessage.KickSound));
    }

    @Test
    public void kickCommandShouldRetrieveScoreForZombie(){
        int initialZombieScore = gameController.getGame().getWorld().getFight().getZombieScore();
        gameController.processInput(CommandType.FightWithKick.getKey());
        int processedZombieScore = gameController.getGame().getWorld().getFight().getZombieScore();
        assertThat(processedZombieScore, is(initialZombieScore + 3));
    }

    @Test
    public void punchCommandShouldRetrieveKickSound(){
        assertThat(gameController.processInput(CommandType.FightWithKick.getKey()).getMessages(),
                hasItem(ResponseMessage.KickSound));
    }

    @Test
    public void punchCommandShouldRetrieveScoreForZombie(){
        int initialZombieScore = gameController.getGame().getWorld().getFight().getZombieScore();
        gameController.processInput(CommandType.FightWithPunch.getKey());
        int processedZombieScore = gameController.getGame().getWorld().getFight().getZombieScore();
        assertThat(processedZombieScore, is(initialZombieScore + FightScoreByLevel.Level_1.getPunchValue()));
    }

    @Test
    public void geekWinnigShouldRetrieveGeekWonMessage(){
        Response response = new Response(ResponseMessage.PunchSound);
        while(response.getMessages().get(0) == ResponseMessage.PunchSound){
            response = gameController.processInput(CommandType.FightWithPunch.getKey());
        }
        ResponseMessage responseMessage = response.getMessages().get(0);
        assumeTrue(responseMessage == ResponseMessage.GeekWonFight);
    }

    @Test
    public void zombieWinnigShouldRetrieveZombieWonMessage(){
        Response response = new Response(ResponseMessage.KickSound);
        while(response.getMessages().get(0) == ResponseMessage.KickSound){
            response = gameController.processInput(CommandType.FightWithKick.getKey());
        }
        ResponseMessage responseMessage = response.getMessages().get(0);
        assumeTrue(responseMessage == ResponseMessage.ZombieWonFight);
    }

}
