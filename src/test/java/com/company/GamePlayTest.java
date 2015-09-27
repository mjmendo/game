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

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class GamePlayTest {
    private GameController gameController;

    @Before
    public void before(){
        ApplicationContext ac = new ApplicationContext();
        gameController =  new GameController(ac.systemLocator, ac.commandFactory, new Game(ac.world, ac.history, GameState.Explore, Level.Level_1, new CustomCharacter()));
    }

    @Test
    public void nullInputShouldShouldRetrieveNotNullMessage(){
        assertNotNull(gameController.processInput("").getMessages().get(0));
    }

    @Test
    public void emptyInputShouldShouldRetrieveNotNullMessage(){
        assertNotNull(gameController.processInput("").getMessages().get(0));
    }
    
    @Test
    public void quitInputShouldRetrieveQuitResponse(){
        assertThat(gameController.processInput(CommandType.Quit.getKey()).getMessages(),
                hasItem(ResponseMessage.QuitTheGame));
    }

}
