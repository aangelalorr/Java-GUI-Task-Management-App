/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanagerapp;

/**
 *
 * @author angel
 */
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import java.util.function.Consumer;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.text.Font;

public class TaskController {
    private final List<Task> taskList = new ArrayList<>();

    private final Consumer<List<Task>> updateTaskList;

    // Constructor to accept updateTaskList reference
    public TaskController(Consumer<List<Task>> updateTaskList) {
        this.updateTaskList = updateTaskList;
    }
    
    public void addTask(Task task) {
        taskList.add(task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskList);
    }
    
    public void removeTask(Task task) {
    taskList.remove(task);
}

    public List<Task> getPendingTasks() {
        return taskList.stream()
                       .filter(task -> !task.isCompleted())
                       .collect(Collectors.toList());
    }

    public List<Task> getCompletedTasks() {
        return taskList.stream()
                       .filter(Task::isCompleted)
                       .collect(Collectors.toList());
    }

    public HBox createTaskNode(Task task) {
        Label taskLabel = new Label(task.toString());
        taskLabel.setFont(Font.font("Comic Sans MS", 15));
    
    // Make the label expand to fill available horizontal space
        HBox.setHgrow(taskLabel, Priority.ALWAYS);
        taskLabel.setMaxWidth(Double.MAX_VALUE);
        
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(task.isCompleted());

        checkBox.setOnAction(e -> {
        task.setCompleted(checkBox.isSelected());
        updateTaskList.accept(getAllTasks()); // Rebuilds the task list
    });

        //String baseStyle = "-fx-background-size: cover; -fx-border-color: BLACK; -fx-border-width: 1; -fx-border-radius: 3; -fx-background-radius: 3;";
        String completedStyle = "-fx-text-fill: grey; -fx-strikethrough: true;";
        String pendingStyle = "-fx-text-fill: black;";
        
       if (task.isCompleted()) {
            taskLabel.setStyle(completedStyle);
        } else {
            taskLabel.setStyle(pendingStyle);
        }
       
        checkBox.setGraphic(taskLabel);
        
        Button deleteButton = new Button(" x ");
        deleteButton.setStyle("-fx-text-fill: red;");

        taskLabel.setStyle(taskLabel.getStyle() + "-fx-padding: 5;");
        deleteButton.setStyle("-fx-text-fill: red; -fx-padding: 5 10;");

        deleteButton.setOnAction(e -> {
            taskList.remove(task);
            updateTaskList.accept(getAllTasks());
        });

    
        HBox hbox = new HBox(10, checkBox, taskLabel, deleteButton);
        hbox.setAlignment(Pos.CENTER_LEFT);
        return hbox;
    }
}