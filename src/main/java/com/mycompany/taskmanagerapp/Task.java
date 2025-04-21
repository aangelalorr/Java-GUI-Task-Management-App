/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanagerapp;

/**
 * 
 * @author angel
 */
public class Task {
    private String description;
    private String date;
    private String time;
    private boolean completed;
    
    public Task(String description, String date, String time, boolean completed){
        this.description = description;
        this.date = date;
        this.time = time;
        this.completed = completed;
        
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setDate(String date){
        this.date = date;
    }
    
    public String getTime(){
        return time;
    }
    
    public void setTime(String time){
        this.time = time;
    }
    
    public boolean isCompleted(){
        return completed;
    }
    
    public void setCompleted(boolean completed){
        this.completed = completed;
    }
    

    
    @Override
    public String toString(){
        return description + " on " + date + " at " + time;
    }
            
}
