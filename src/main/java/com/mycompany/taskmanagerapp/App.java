package com.mycompany.taskmanagerapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {
    
    private TextField taskField, timeField;
    private DatePicker datePicker;
    private Button addButton, allTaskButton, pendingButton, completedButton;
    //private CheckBox checkbox;
    private VBox taskListVBox;
    private TaskController controller;
    //private VBox taskListVBox;
    
    
        public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) {
        controller = new TaskController(this::updateTaskList);
        //controller.setUpdateCallback(this::updateTaskList);
        
        taskListVBox = new VBox();
        
        StackPane root = new StackPane();
        Image image = new Image("file:C:\\Users\\angel\\Downloads\\beige.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(false);
        imageView.fitWidthProperty().bind(root.widthProperty());
        imageView.fitHeightProperty().bind(root.heightProperty());
        
  
        Image fillImage = new Image("file:C:\\Users\\angel\\Downloads\\sagegreen.jpg");
        Rectangle displayBox = new Rectangle(500, 600);
        displayBox.setArcWidth(20);
        displayBox.setArcHeight(20);
        displayBox.setFill(new ImagePattern(fillImage));
        displayBox.setStroke(Color.BLACK);
        displayBox.setStrokeWidth(2);
        
        //title
        Label titleLabel = new Label("Task Manager");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: black; -fx-font-family: 'Comic Sans MS';");
        titleLabel.setAlignment(Pos.CENTER);

        taskField = new TextField();
        taskField.setStyle("-fx-border-color: BLACK; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-radius: 5;");

        datePicker = new DatePicker();
        datePicker.setStyle("-fx-border-color: BLACK; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-radius: 5;");
        //datePicker.setOnAction(this::processDateChoice);
        
        timeField = new TextField();
        timeField.setStyle("-fx-border-color: BLACK; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-radius: 5;");

        

        taskField.setPromptText("Enter a task...");
        taskField.setPrefWidth(150);

        datePicker.setPromptText("Date");
        datePicker.setPrefWidth(150);

        timeField.setPromptText("Time");
        timeField.setPrefWidth(80);

        //add button
        addButton = new Button("Add Task");
        addButton.setFont(Font.font("Comic Sans MS", 13));
        addButton.setStyle("-fx-background-image: url('file:C:///Users/angel/Downloads/brown.jpg'); -fx-background-size: cover;-fx-border-color: BLACK; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-radius: 5;");
        addButton.setTextFill(Color.BLACK);
        addButton.setPrefWidth(100);
        addButton.setPrefHeight(10);
        addButton.setOnAction(this::handleAddTask);
        
        allTaskButton = new Button("All Tasks");
        allTaskButton.setFont(Font.font("Comic Sans MS", 10));
        allTaskButton.setStyle("-fx-background-image: url('file:C:///Users/angel/Downloads/brown.jpg'); -fx-background-size: cover;-fx-border-color: BLACK; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-radius: 5;");
        allTaskButton.setTextFill(Color.BLACK);
        allTaskButton.setPrefWidth(150);
        allTaskButton.setPrefHeight(10);
        allTaskButton.setOnAction(this::handleShowAllTasks);
        
        
        pendingButton = new Button("Pending Tasks");
        pendingButton.setFont(Font.font("Comic Sans MS", 10));
        pendingButton.setStyle("-fx-background-image: url('file:C:///Users/angel/Downloads/brown.jpg'); -fx-background-size: cover;-fx-border-color: BLACK; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-radius: 5;");
        pendingButton.setTextFill(Color.BLACK);
        pendingButton.setPrefWidth(150);
        pendingButton.setPrefHeight(10);
        pendingButton.setOnAction(this::handleShowPendingTasks);
        
        completedButton = new Button("Completed Tasks");
        completedButton.setFont(Font.font("Comic Sans MS", 10));
        completedButton.setStyle("-fx-background-image: url('file:C:///Users/angel/Downloads/brown.jpg'); -fx-background-size: cover;-fx-border-color: BLACK; -fx-border-width: 1; -fx-border-radius: 5; -fx-background-radius: 5;");
        completedButton.setTextFill(Color.BLACK);
        completedButton.setPrefWidth(150);
        completedButton.setPrefHeight(10);
        completedButton.setOnAction(this::handleShowCompletedTasks);
        
        
        HBox hbox = new HBox(10, datePicker, timeField, addButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10));
        
        HBox more = new HBox(10, allTaskButton, pendingButton, completedButton);
        more.setAlignment(Pos.CENTER);
        more.setPadding(new Insets(10));
        
        taskListVBox = new VBox(10);
        taskListVBox.setPadding(new Insets(10));
        


        ScrollPane scrollPane = new ScrollPane(taskListVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(Double.MAX_VALUE);
        //scrollPane.setMaxHeight(550); // You can adjust the height as needed
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox formVBox = new VBox(10, titleLabel, taskField, hbox, more, scrollPane);
        formVBox.setAlignment(Pos.TOP_CENTER);
        formVBox.setPadding(new Insets(20));
        formVBox.setMaxWidth(displayBox.getWidth() - 40);
        formVBox.setMaxHeight(displayBox.getHeight() - 40);
        formVBox.setStyle("-fx-background-color: transparent;");
        
        StackPane formPane = new StackPane(displayBox, formVBox);
        formPane.setMaxWidth(displayBox.getWidth());
        formPane.setMaxHeight(displayBox.getHeight());
        


        
        StackPane Pane = new StackPane(displayBox, formVBox);
        Pane.setMaxWidth(displayBox.getWidth());
        Pane.setMaxHeight(displayBox.getHeight());

        root.getChildren().addAll(imageView, Pane);


        Scene scene = new Scene(root, 600, 700);

        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Task Manager App");
        primaryStage.show();
        

        
        
        
        
        
    }
    
    @SuppressWarnings("unused")
    private void handleAddTask(ActionEvent event) {
    String taskText = taskField.getText().trim();
    LocalDate date = datePicker.getValue();
    String time = timeField.getText().trim();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    String formattedDate = date.format(formatter);

    if (!taskText.isEmpty() && !time.isEmpty()) {
        //String formattedDate = date != null ? date.toString() : "No Date";
        Task newTask = new Task(taskText, formattedDate, time, false);
        controller.addTask(newTask);
        taskField.clear();
        timeField.clear();
        datePicker.setValue(null);
        updateTaskList(controller.getAllTasks());
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText("Missing Task Details");
        alert.setContentText("Please enter a task, date, and time.");
        alert.showAndWait();
    }
}

    @SuppressWarnings("unused")
    private void handleShowAllTasks(ActionEvent event) {
        updateTaskList(controller.getAllTasks());
}

    @SuppressWarnings("unused")
    private void handleShowPendingTasks(ActionEvent event) {
        updateTaskList(controller.getPendingTasks());
}

    @SuppressWarnings("unused")
    private void handleShowCompletedTasks(ActionEvent event) {
        updateTaskList(controller.getCompletedTasks());
}



   
    private void updateTaskList(List<Task> tasks) {
        taskListVBox.getChildren().clear();
        for (Task task : tasks) {
            HBox taskNode = controller.createTaskNode(task);
            taskListVBox.getChildren().add(taskNode);
        }
    }
}    
    
