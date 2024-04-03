package org.app.cpsc4140hw1;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.util.Duration;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class App extends javafx.application.Application {

    private static final int MAX_TRIALS = 10;

    // Constants
    private static final double CIRCLE_RADIUS_FACTOR = 0.155;
    private static final String[] COLORS = {"RED", "GREEN", "BLUE", "YELLOW", "ORANGE"};

    private List<TrialData> trialDataList = new ArrayList<>();
    private int currentTrialNumber = 1;
    private long startTime;
    private double startX;
    private double startY;


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


        goMenuItem.setOnAction(event -> startCountdown(root));


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

        // Generate a random circle size within a range
        double minCircleRadius = Math.min(windowWidth, windowHeight) * CIRCLE_RADIUS_FACTOR / 2;
        double maxCircleRadius = Math.min(windowWidth, windowHeight) * CIRCLE_RADIUS_FACTOR;
        double circleRadius = minCircleRadius + Math.random() * (maxCircleRadius - minCircleRadius);

        // Random position within the window
        double randomX = Math.random() * (windowWidth - 1 - circleSize);
        double randomY = Math.random() * (windowHeight - 1 - circleSize);


        // Create a random circle
        Circle circle = new Circle(randomX, randomY, circleRadius);
        circle.setFill(Color.valueOf(getRandomColor()));

        // Record target data
        recordTargetData(circleRadius * 2, randomX - circleRadius, randomY - circleRadius);

        // Set the mouse click event handler for the circle
        circle.setOnMouseClicked(event -> handleMouseClickOnCircle(event));

        // Add the circle to the root
        root.getChildren().add(circle);
    }




    private void recordTargetData(double size, double x, double y) {
        double distance = Math.sqrt(Math.pow(x - startX, 2) + Math.pow(y - startY, 2)); // Calculate the distance from the start position to the target
        TrialData trialData = new TrialData(currentTrialNumber, size, distance);
        trialDataList.add(trialData);
        startTime = System.currentTimeMillis(); // Record the start time for this trial
    }


    private void handleMouseClickOnCircle(MouseEvent event) {
        long endTime = System.currentTimeMillis();
        long timeToClick = endTime - startTime;
        TrialData currentTrial = trialDataList.get(currentTrialNumber - 1);
        currentTrial.setTimeToClick(timeToClick);

        currentTrialNumber++;

        if (currentTrialNumber > MAX_TRIALS) {
            writeTrialDataToCSV(); // Write trial data to CSV file
            System.exit(0);
        } else {
            Node sourceNode = (Node) event.getSource();
            displayRandomCircle((BorderPane) sourceNode.getScene().getRoot());
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

    private void startCountdown(BorderPane root) {
        Label countdownLabel = new Label("5");
        countdownLabel.setStyle("-fx-font-size: 60px;"); // Increase the font size
        countdownLabel.setAlignment(Pos.CENTER); // Align the text to the center
        countdownLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Allow the label to expand to the maximum size

        root.setCenter(countdownLabel); // Position the label in the center of the BorderPane

        Timeline countdown = new Timeline(
                new KeyFrame(Duration.seconds(1), new KeyValue(countdownLabel.textProperty(), "4")),
                new KeyFrame(Duration.seconds(2), new KeyValue(countdownLabel.textProperty(), "3")),
                new KeyFrame(Duration.seconds(3), new KeyValue(countdownLabel.textProperty(), "2")),
                new KeyFrame(Duration.seconds(4), new KeyValue(countdownLabel.textProperty(), "1")),
                new KeyFrame(Duration.seconds(5), ae -> {
                    root.setCenter(null); // Remove the label after the countdown
                    displayRandomCircle(root);
                })
        );
        countdown.play();
    }

    private void writeTrialDataToCSV() {
        String fileName = "trial_data.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Trial Number, Target Size (in pixels), Distance to Target (in pixels), Time to Click (in milliseconds)\n");
            for (TrialData trial : trialDataList) {
                writer.write(trial.toCSVString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
