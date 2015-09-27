package com.company.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class World implements Serializable{

    private Maze maze;

    private Zombie zombie;

    private Fight fight;

}
