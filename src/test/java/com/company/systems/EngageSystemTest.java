package com.company.systems;

import com.company.commands.Command;
import com.company.context.ApplicationContext;
import com.company.entities.CustomCharacter;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;
import com.company.entities.level.Level;
import com.company.sytems.EngageSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class EngageSystemTest {

    private Game game;
    private Command command;
    private EngageSystem engageSystem;

    @Before
    public void before(){
        ApplicationContext applicationContext = new ApplicationContext();
        game = new Game(applicationContext.world, applicationContext.history,
                GameState.Fight, Level.Level_1, new CustomCharacter());
        command = mock(Command.class);
        engageSystem = applicationContext.engageSystem;
        
    }

    @Test
    public void messageShouldNotBeNull(){
        assertNotNull("Message should not be null", engageSystem.execute(game, command));
    }
}
