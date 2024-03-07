package org.app.cpsc4140hw1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Optional;
import java.util.Random;


public class App extends javafx.application.Application {


    // Constants
    private static final double CIRCLE_RADIUS_FACTOR = 0.125;
    private static final String[] COLORS = {"RED", "GREEN", "BLUE", "YELLOW", "ORANGE"};


    /**
     * Entry point for the app
     *
     * @param stage The primary stage for this app.
     */
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        // Create a menuBar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");


        // Create menu items
        MenuItem goMenuItem = new MenuItem("Go!");
        MenuItem quitMenuItem = new MenuItem("Quit");


        goMenuItem.setOnAction(event -> displayRandomCircle(root));


        quitMenuItem.setOnAction(event -> stage.close());


        fileMenu.getItems().addAll(goMenuItem, quitMenuItem);


        // Add the file menu to the menuBar
        menuBar.getMenus().add(fileMenu);


        // Add the menuBar to the top of the BorderPane
        root.setTop(menuBar);


        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("hello-view.fxml"));
        root.setCenter(fxmlLoader.load());


        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("CPSC4140");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Displays a random circle in the app
     *
     * @param root The BorderPane on which to display the circle.
     *
     * @pre root must not be null.
     * @post The BorderPane will have a new Circle displayed on it.
     */
    private void displayRandomCircle(BorderPane root) {
        // Remove any existing circles
        root.getChildren().removeIf(node -> node instanceof Circle);


        // Circle size based on the dimensions
        double windowWidth = root.getWidth();
        double windowHeight = root.getHeight();
        double circleSize = Math.min(windowWidth, windowHeight) * CIRCLE_RADIUS_FACTOR;


        // Random position within the window
        double randomX = Math.random() * (windowWidth - circleSize);
        double randomY = Math.random() * (windowHeight - circleSize);


        // Create a random circle
        Circle circle = new Circle(randomX + circleSize / 2, randomY + circleSize / 2, circleSize);
        circle.setFill(Color.valueOf(getRandomColor()));


        // Set the mouse click event handler for the circle
        circle.setOnMouseClicked(event -> handleMouseClickOnCircle(event));


        // Add the circle to the root
        root.getChildren().add(circle);
    }


    /**
     * Handles the mouse click event on the circle.
     *
     * @param event The MouseEvent representing the mouse click.
     *
     * @pre event must not be null.
     * @post If the mouse click occurs, an information alert will be displayed.
     */
    private void handleMouseClickOnCircle(MouseEvent event) {
        // Display an information alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Circle Selected");
        alert.setHeaderText(null);
        alert.setContentText("The circle has been selected.");


        // Add an OK button to the alert
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);


        // Show the alert and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();


        // If OK is clicked, close the app
        if (result.isPresent() && result.get() == okButton) {
            System.exit(0);
        }
    }


    /**
     * Generates a random color from the COLORS array.
     *
     * @return A randomly selected color from the COLORS array.
     *
     * @pre The returned color is a valid color from the COLORS array.
     */
    private String getRandomColor() {
        Random random = new Random();
        return COLORS[random.nextInt(COLORS.length)];
    }


    /**
     * The main method to launch the app
     *
     * @param args Command-line arguments.
     *
     * @post The app is launched.
     */
    public static void main(String[] args) {
        launch();
    }
}

