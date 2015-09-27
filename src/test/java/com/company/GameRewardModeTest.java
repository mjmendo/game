package com.company;

import com.company.context.ApplicationContext;
import com.company.context.CommandType;
import com.company.controller.GameController;
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

public class GameRewardModeTest {

    private GameController gameController;

    @Before
    public void before(){
        ApplicationContext ac = new ApplicationContext();
        gameController =  new GameController(ac.systemLocator, ac.commandFactory, new Game(ac.world, ac.history,
                GameState.Reward, Level.Level_1, new CustomCharacter()));
    }
    
    @Test
    public void eatBrainCommandShouldRetrieveYummyMessage(){
        assertThat(gameController.processInput(CommandType.EatBrain.getKey()).getMessages(),
                contains(ResponseMessage.EatBrain, ResponseMessage.Explore));
        
    }

    @Test
    public void keepExploringCommandShouldRetrieveExploreMessage(){
        assertThat(gameController.processInput(CommandType.KeepExploring.getKey()).getMessages(),
                hasItem(ResponseMessage.Explore));
    }

    @Test
    public void gameShouldChangeStateToExplore(){
        gameController.processInput(CommandType.EatBrain.getKey());
        assertThat(gameController.getGame().getState(), is(GameState.Explore));

    }

    @Test
    public void invalidWorldStateShouldRetrieveNoBrainMessage(){
        gameController.getGame().setState(GameState.Explore);
        assertThat(gameController.processInput(CommandType.EatBrain.getKey()).getMessages(),
                contains(ResponseMessage.ShouldBeExploring, ResponseMessage.Explore));

    }
}
