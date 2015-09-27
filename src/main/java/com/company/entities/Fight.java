package com.company.entities;

import com.company.components.Position;
import lombok.Data;

import java.io.Serializable;

@Data
public class Fight implements Serializable{
    private int zombieScore;
    private int geekScore;
    private Position geekPosition;

}
