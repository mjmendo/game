package com.company;

import com.company.context.ApplicationContext;
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
import static org.junit.Assert.assertThat;

public class LoadSavedGameModeTest {
    private GameController gameController;
    private Response response;

    @Before
    public void before(){
        ApplicationContext ac = new ApplicationContext();
        gameController =  new GameController(ac.systemLocator, ac.commandFactory, new Game(ac.world, ac.history,
                GameState.LoadSavedGame, Level.Level_1, new CustomCharacter()));
    }

    @Test
    public void commandAtLoadSaveGameStateShouldGetListOfSavedGames(){
        assertThat(gameController.processInput("").getMessages(),
                contains(ResponseMessage.UnknownCommand, ResponseMessage.LoadSavedGame));
    }

}
