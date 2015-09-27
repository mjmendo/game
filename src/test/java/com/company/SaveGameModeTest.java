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
import static org.junit.Assert.assertThat;

public class SaveGameModeTest {
    private GameController gameController;
    private Response response;

    @Before
    public void before(){
        ApplicationContext ac = new ApplicationContext();
        gameController =  new GameController(ac.systemLocator, ac.commandFactory, new Game(ac.world, ac.history,
                GameState.SaveGame, Level.Level_1, new CustomCharacter()));
    }

    @Test
    public void saveGameStateShouldRetrieveSaveGameQuestion(){
        assertThat(gameController.processInput("").getMessages(),
                contains(ResponseMessage.UnknownCommand, ResponseMessage.SaveGameQuestion));
    }

    @Test
    public void okCommandShouldReturnSavedMessage(){
        assertThat(gameController.processInput(CommandType.Ok.getKey()).getMessages(),
                contains(ResponseMessage.GameSaved, ResponseMessage.Explore));
    }

    @Test
    public void cancelCommandShouldResturnExplore(){
        assertThat(gameController.processInput(CommandType.Cancel.getKey()).getMessages(),
                contains(ResponseMessage.Explore));
    }

}
