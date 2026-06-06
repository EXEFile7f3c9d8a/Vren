package vren.debug.vernbrowsercore;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UILauncherTest extends Application {
    public static void main(String[] args){
        javafx.application.Application.launch(vren.debug.vernbrowsercore.UILauncherTest.class, args);
    }

    public void start(Stage primaryStage) throws Exception{
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
