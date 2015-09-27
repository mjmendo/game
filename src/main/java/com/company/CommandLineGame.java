package com.company;


import com.company.context.ApplicationContext;
import com.company.controller.GameController;
import com.company.controller.Response;
import com.company.controller.ResponseMessage;
import com.company.entities.CustomCharacter;
import com.company.entities.game.Game;
import com.company.entities.game.GameState;
import com.company.entities.level.Level;
import com.company.exception.GameException;
import com.company.entities.stats.history.GameHistory;

import java.util.Scanner;

public class CommandLineGame
{

    private static GameController gameController;
    private static ApplicationContext applicationContext;

    public static void main( String[] args )
    {
        try (Scanner scanner = new Scanner(System.in)) {
            applicationContext = new ApplicationContext();
            gameController = new GameController(applicationContext.systemLocator, applicationContext.commandFactory,
                    new Game(applicationContext.world, new GameHistory(), GameState.MainMenu, Level.Level_1, new CustomCharacter()));

            applicationContext.ioPrinter.print(applicationContext.prop.getProperty("welcome"));
            applicationContext.ioPrinter.print(applicationContext.prop.getProperty("menu"));


            while(true){

                Response response = gameController.processInput(scanner.nextLine());
                
                for(ResponseMessage responseMessage : response.getMessages()){
                    String message = formatMessage(applicationContext, responseMessage);
                    applicationContext.ioPrinter.print(message);
                }
                
                if( response.getMessages().contains(ResponseMessage.QuitTheGame)
                        || response.getMessages().contains(ResponseMessage.GeekWonFight)){
                    break;
                }
            }

        } catch (GameException e) {
            System.out.println("Error occurred while starting game: " + e.getMessage());
        } catch (Throwable e) {
            System.out.println("Uncatched error occurred while starting game: " + e.getMessage());
            e.printStackTrace();
        } finally {
            gameController.getGame().getHistory().getRecords().forEach(System.out::println);
        }

    }

    private static String formatMessage(ApplicationContext applicationContext, ResponseMessage responseMessage) {
        String message = applicationContext.prop.getProperty(responseMessage.getMessageKey());
        if(responseMessage.hasParams()) {
            int paramIndex = 0;
            for(String param : responseMessage.getMessageParams()){
                if(param == null) continue;
                
                String property = applicationContext.prop.getProperty(param);
                if(property != null)
                    responseMessage.getMessageParams()[paramIndex] = property;
                
                paramIndex++;
            }

            message = String.format(message, responseMessage.getMessageParams());
        }
        return message;
    }


}
