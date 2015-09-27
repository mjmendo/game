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
import static org.junit.Assert.assertThat;

public class MainMenuModeTest {
    private GameController gameController;

    @Before
    public void before(){
        ApplicationContext ac = new ApplicationContext();
        gameController =  new GameController(ac.systemLocator, ac.commandFactory, 
                new Game(ac.world, ac.history, GameState.MainMenu, Level.Level_1, new CustomCharacter()));
    }

    @Test
    public void selectPlayShouldGetCreateCharQuestion(){
        assertThat(gameController.processInput(CommandType.Option_1.getKey()).getMessages(),
                contains(ResponseMessage.CreateCharQuestion));
    }

    @Test
    public void selectLoadShouldGetCreateCharQuestion(){
        assertThat(gameController.processInput(CommandType.Option_2.getKey()).getMessages(),
                contains(ResponseMessage.LoadSavedGame));
    }

    @Test
    public void selectLoadShouldChangeStatusToLoadSavedGame(){
        gameController.processInput(CommandType.Option_2.getKey());
        assertThat(gameController.getGame().getState(), is(GameState.LoadSavedGame));
    }


}
