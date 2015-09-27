package com.company;

import com.company.context.ApplicationContext;
import com.company.context.CommandType;
import com.company.controller.GameController;
import com.company.controller.Response;
import com.company.controller.ResponseMessage;
import com.company.entities.CustomCharacter;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;
import com.company.entities.level.Level;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class GameEngageModeTest {
    private GameController gameController;
    private Response response;

    @Before
    public void before(){
        ApplicationContext ac = new ApplicationContext();
        gameController =  new GameController(ac.systemLocator, ac.commandFactory, new Game(ac.world, ac.history, GameState.Explore, Level.Level_1, new CustomCharacter()));
        CommandType[] batchToFindGeekOnMaze = new CommandType[]{CommandType.MoveEast, CommandType.MoveEast, CommandType.MoveEast};
        for (CommandType commandType : batchToFindGeekOnMaze){
            response = gameController.processInput(commandType.getKey());
        }
    }

    @Test
    public void findingAGeekOnMazeShouldGetEngageQuestionMessage(){
        assertThat(response.getMessages(),
                hasItem(ResponseMessage.EngageQuestion));
    }

    @Test
    public void findingAGeekOnMazeShouldChangeWorldStateToEngage(){
        assertThat(gameController.getGame().getState(), is(GameState.Engage));
    }

    @Test
    public void discussCommandAtEngageStateShouldReturnDiscussionTopic(){
        assertThat(gameController.processInput(CommandType.EngageInTalk.getKey()).getMessages(),
                hasItem(ResponseMessage.InfoQDiscuss));
    }

    @Test
    public void fightCommandAtEngageStateShouldReturnDiscussionTopic(){
        assertThat(gameController.processInput(CommandType.EngageInFight.getKey()).getMessages(),
                hasItem(ResponseMessage.EngageStart));
    }

    @Test
    public void engageCommandAtNoEngageModeShouldReturnNothingToFight(){
        ApplicationContext ac = new ApplicationContext();
        gameController =  new GameController(ac.systemLocator, ac.commandFactory, new Game(ac.world, ac.history, GameState.Explore, Level.Level_1, new CustomCharacter()));
        gameController.getGame().setState(GameState.Explore);
        assertThat(gameController.processInput(CommandType.EngageInFight.getKey()).getMessages(),
                contains(ResponseMessage.ShouldBeExploring, ResponseMessage.Explore));
    }

}
