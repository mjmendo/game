package com.company.systems;

import com.company.commands.DefaultCommand;
import com.company.components.Position;
import com.company.context.ApplicationContext;
import com.company.context.CommandType;
import com.company.controller.ResponseMessage;
import com.company.entities.CustomCharacter;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;
import com.company.entities.level.Level;
import com.company.sytems.MovementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class MovementSystemTest {

    private MovementSystem movementSystem;
    private DefaultCommand defaultCommand;
    private Game game;

    @Before
    public void before(){
        ApplicationContext ac = new ApplicationContext();
        game = new Game(ac.world, ac.history, GameState.Explore, Level.Level_1, new CustomCharacter());
        movementSystem = ac.movementSystem;
    }

    @Test
    public void moveEastShouldBeAllowed() {
        defaultCommand = new DefaultCommand(CommandType.MoveEast);
        assertThat(movementSystem.execute(game, defaultCommand).getMessages(),
                hasItem(ResponseMessage.Good));
    }

    @Test
    public void testWestWall() {
        defaultCommand = new DefaultCommand(CommandType.MoveWest);

        assertThat(movementSystem.execute(game, defaultCommand).getMessages(),
                hasItem(ResponseMessage.Wall));
    }

    @Test
    public void testFindGeek() {
        game.getWorld().getZombie().setPosition(new Position(1,3));
        defaultCommand = new DefaultCommand(CommandType.MoveEast);
        assertThat(movementSystem.execute(game, defaultCommand).getMessages(),
                hasItem(ResponseMessage.EngageQuestion));
    }

}
