package com.company.files;

import com.company.entities.game.Game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {
    
    public String saveGame(Game game){
        FileOutputStream fout = null;
        String filename = null;
        try {
            filename = game.getCharacter().getName() != null ? game.getCharacter().getName() + "-" : "";
            filename += getFormattedDateRepresentation();
            Files.createDirectories(new File("./saved").toPath());
            fout = new FileOutputStream("./saved/" + filename + ".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(game);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filename;
    }
    
    public String getFormattedDateRepresentation(){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH_mm_ss");
        return date.format(formatter);
        
    }
}
