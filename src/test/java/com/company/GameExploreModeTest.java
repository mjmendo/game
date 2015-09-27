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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class GameExploreModeTest {
    private GameController gameController;

    @Before
    public void before(){
        ApplicationContext ac = new ApplicationContext();
        gameController =  new GameController(ac.systemLocator, ac.commandFactory, new Game(ac.world, ac.history, GameState.Explore, Level.Level_1, new CustomCharacter()));
    }

    @Test
    public void shouldMoveNorth(){
        assertThat(gameController.processInput(CommandType.MoveNorth.getKey()).getMessages(),
                hasItem(ResponseMessage.Wall));
    }

    @Test
    public void shouldMoveEast(){
        assertThat(gameController.processInput(CommandType.MoveEast.getKey()).getMessages(),
                hasItem(ResponseMessage.Good));
    }

    @Test
    public void shouldMoveSouth(){
        assertThat(gameController.processInput(CommandType.MoveSouth.getKey()).getMessages(),
                hasItem(ResponseMessage.Good));
    }

    @Test
    public void shouldMoveWest(){
        assertThat(gameController.processInput(CommandType.MoveWest.getKey()).getMessages(),
                hasItem(ResponseMessage.Wall));
    }

    @Test
    public void saveCommandShouldGetSaveGameQuestion(){
        assertThat(gameController.processInput(CommandType.Save.getKey()).getMessages(),
                hasItem(ResponseMessage.SaveGameQuestion));
    }

    @Test
    public void saveCommandShouldChangeStatusToSaveMode(){
        gameController.processInput(CommandType.Save.getKey());
        assertThat(gameController.getGame().getState(),
                is(GameState.SaveGame));
    }

}
