package vren.vrenbrowsercore;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Show extends Application{
    public static void main(String[] args){
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label Text = new Label("Welcome to Vren");
        Button button = new Button("Button for clicking");
        button.setOnAction(event -> {
            if(Text.getText().equals("Button Clicked")){
                Text.setText("Welcome to Vren");
            }else Text.setText("Button Clicked");
        });
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(Text, button);
        Scene scene = new Scene(vBox, 1600, 950);
        primaryStage.setTitle("Vren");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
