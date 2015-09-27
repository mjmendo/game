package com.company.context;

import com.company.CommandLineGame;
import com.company.components.Position;
import com.company.entities.Fight;
import com.company.entities.Maze;
import com.company.entities.World;
import com.company.entities.Zombie;
import com.company.exception.GameException;
import com.company.files.FileManager;
import com.company.printer.IOPrinter;
import com.company.entities.stats.history.GameHistory;
import com.company.sytems.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ApplicationContext {

    public final Properties prop;
    public final IOPrinter ioPrinter;
    public final CommandFactory commandFactory;
    public final SystemLocator systemLocator;
    public final World world;
    public final GameHistory history;
    public final Maze maze;
    public final Zombie zombie;
    public final MovementSystem movementSystem;
    public final EngageSystem engageSystem;
    public final FightSystem fightSystem;
    public final RewardSystem rewardSystem;
    public final NextLevelSystem nextLevelSystem;
    public final MainMenuSystem mainMenuSystem;
    public final CreateCharSystem createCharSystem;
    public final SaveGameSystem saveGameSystem;
    public final FileManager fileManager;
    
    public Map<Class<? extends GameSystem>, List<CommandType>> allowedCommandsBySystem;

    /**
     * No frameworks allowed? No problem! Home-made DI pattern at its finest
     */
    public ApplicationContext(){
        try {
            prop = loadProperties();
            ioPrinter = new IOPrinter();
            setupAllowedCommands();
            fileManager = new FileManager();

            //-- Game entities
            maze = new Maze();
            zombie = new Zombie();
            zombie.setPosition(new Position(1,1));
            world = new World(maze, zombie, new Fight());
            history = new GameHistory();

            //-- Systems
            movementSystem = new MovementSystem(allowedCommandsBySystem.get(MovementSystem.class));
            engageSystem = new EngageSystem(allowedCommandsBySystem.get(EngageSystem.class));
            fightSystem = new FightSystem(allowedCommandsBySystem.get(FightSystem.class));
            rewardSystem = new RewardSystem(allowedCommandsBySystem.get(RewardSystem.class));
            nextLevelSystem = new NextLevelSystem(allowedCommandsBySystem.get(NextLevelSystem.class));
            mainMenuSystem = new MainMenuSystem(allowedCommandsBySystem.get(MainMenuSystem.class));
            createCharSystem = new CreateCharSystem(allowedCommandsBySystem.get(CreateCharSystem.class));
            saveGameSystem = new SaveGameSystem(allowedCommandsBySystem.get(SaveGameSystem.class), new FileManager());
            
            commandFactory = new CommandFactory(this);
            systemLocator = new SystemLocator(this);

        } catch (Exception e){
            throw new GameException("Error creating application context", e);
        }
    }
    
    public void setupAllowedCommands(){
        allowedCommandsBySystem = new HashMap<>();
        allowedCommandsBySystem.put(MovementSystem.class, new ArrayList(){{
            add(CommandType.MoveEast);
            add(CommandType.MoveNorth);
            add(CommandType.MoveSouth);
            add(CommandType.MoveWest);
            add(CommandType.Save);

        }});

        allowedCommandsBySystem.put(EngageSystem.class, new ArrayList(){{
            add(CommandType.EngageInFight);
            add(CommandType.EngageInTalk);

        }});

        allowedCommandsBySystem.put(FightSystem.class, new ArrayList(){{
            add(CommandType.FightWithKick);
            add(CommandType.FightWithPunch);

        }});

        allowedCommandsBySystem.put(RewardSystem.class, new ArrayList(){{
            add(CommandType.EatBrain);
            add(CommandType.KeepExploring);


        }});

        allowedCommandsBySystem.put(NextLevelSystem.class, new ArrayList(){{
            add(CommandType.Ok);
            add(CommandType.Cancel);

        }});

        allowedCommandsBySystem.put(MainMenuSystem.class, new ArrayList(){{
            add(CommandType.Option_1);
            add(CommandType.Option_2);

        }});

        allowedCommandsBySystem.put(CreateCharSystem.class, new ArrayList(){{
            add(CommandType.Ok);
            add(CommandType.InputCommand);
            add(CommandType.Cancel);


        }});

        allowedCommandsBySystem.put(SaveGameSystem.class, new ArrayList(){{
            add(CommandType.Ok);
            add(CommandType.Cancel);

        }});
        
    }

    public static Properties loadProperties() throws IOException {
        Properties prop = new Properties();

        InputStream resourceAsStream = CommandLineGame.class.getResourceAsStream("/messages.properties");

        if(resourceAsStream != null) {
            prop.load(resourceAsStream);
            return prop;
        }

        throw new GameException("Error loading properties file. Check if exists and try again.");
    }
}
