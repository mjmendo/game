package com.company.entities;

import com.company.components.Position;

import java.io.Serializable;

public class Maze implements Serializable{

    String[][] maze = {
                {"W", "W","W","W","W","W","W"},
                {"W", " "," "," ","G"," ","W"},
                {"W", " ","W","W"," "," ","W"},
                {"W", " "," "," "," ","W","W"},
                {"W", " "," ","G"," "," ","W"},
                {"W", " ","G"," "," ","G","W"},
                {"W", " "," "," ","G"," ","W"},
                {"W", "W","W","W","W","X","W"}};

    public String getItemAt(Position position) {

        if (position.getX() < 0 || position.getX() >= maze.length || position.getY() < 0 || position.getY() >= maze[0].length)
            return null;

        return maze[position.getX()][position.getY()];
    }
    
    public void deleteItemAt(Position position){
        maze[position.getX()][position.getY()] = "";
    }
}
