package vren.debug.vrenbrowsercore;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UILauncherTest extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){
        Label Text = new Label("Welcome to Vren");
        Button button = new Button("Button for clicking");
        button.setOnAction(event -> Text.setText("Button Clicked"));
        VBox layout = new VBox(10);
        layout.getChildren().addAll(Text, button);
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("Vren");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
