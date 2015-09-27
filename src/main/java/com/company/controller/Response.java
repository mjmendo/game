package com.company.controller;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Response implements Serializable{
    
    private List<ResponseMessage> messages = new ArrayList<>();
    
    public Response(){}
    
    public Response(ResponseMessage message){
        messages.add(message);
        
    }
    
    public void addResponseMessage(ResponseMessage responseMessage){
        messages.add(responseMessage);
    }
    
}
