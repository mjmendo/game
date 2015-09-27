package com.company.entities.stats.history;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GameHistory implements Serializable{
    
    private final List<Record> records = new ArrayList<>();
    
    public void addRecordToHistory(Record record){
        records.add(record);
        
    }
}
