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

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class NextLevelModeTest {
    private GameController gameController;

    @Before
    public void before(){
        ApplicationContext ac = new ApplicationContext();
        gameController =  new GameController(ac.systemLocator, ac.commandFactory, new Game(ac.world, ac.history, GameState.LevelCompleted, Level.Level_1, new CustomCharacter()));
    }

    @Test
    public void nextLevelPlayRequestShouldRetrieveNextLevelMessage(){
        Response response = gameController.processInput(CommandType.Ok.getKey());
        assertThat(response.getMessages(),
                hasItem(ResponseMessage.PlayNextLevel));
    }

    @Test
    public void nextLevelPlayCancelShouldRetrieveNextLevelMessage(){
        Response response = gameController.processInput(CommandType.Cancel.getKey());
        assertThat(response.getMessages(),
                hasItem(ResponseMessage.SaveGameQuestion));
    }



}
