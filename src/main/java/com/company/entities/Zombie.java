package com.company.entities;

import com.company.components.Position;
import lombok.Data;

import java.io.Serializable;


@Data
public class Zombie implements Serializable{
    private Position position;

}
