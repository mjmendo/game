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
import static org.junit.Assert.assertThat;

public class GameCreateCharacterModeTest {
    private GameController gameController;
    private Response response;

    @Before
    public void before(){
        ApplicationContext ac = new ApplicationContext();
        gameController =  new GameController(ac.systemLocator, ac.commandFactory, new Game(ac.world, ac.history,
                GameState.CreateChar, Level.Level_1, new CustomCharacter()));
    }

    @Test
    public void createCharCommandShouldGetNameRequestForChar(){
        assertThat(gameController.processInput(CommandType.Ok.getKey()).getMessages(),
                contains(ResponseMessage.PickANameForChar));
    }

    @Test
    public void cancelCreateCharCommandShouldGetExploreMessage(){
        assertThat(gameController.processInput(CommandType.Cancel.getKey()).getMessages(),
                contains(ResponseMessage.Explore));
    }

    @Test
    public void cancelCreateCharCommandShouldChangeGameStatusToExplore(){
        gameController.processInput(CommandType.Cancel.getKey());
        assertThat(gameController.getGame().getState(),
                is(GameState.Explore));
    }

    @Test
    public void sendingEmptyInputCommandShouldChangeGameStatusToExplore(){
        assertThat(gameController.processInput("").getMessages(),
                contains(ResponseMessage.UnknownCommand, ResponseMessage.CreateCharQuestion));
    }

    @Test
    public void sendingNullInputCommandShouldChangeGameStatusToExplore(){
        assertThat(gameController.processInput(null).getMessages(),
                contains(ResponseMessage.UnknownCommand, ResponseMessage.CreateCharQuestion));
    }

    @Test
    public void sendingValidInputCommandShouldChangeGameStatusToExplore(){
        assertThat(gameController.processInput("diego armando").getMessages(),
                contains(ResponseMessage.CharNameCreated, ResponseMessage.Explore));
    }

    @Test
    public void sendingValidInputCommandShouldUpdateCharName(){
        gameController.processInput("diego armando");
        assertThat(gameController.getGame().getCharacter().getName(),
                is("diego armando"));
    }


}
