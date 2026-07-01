package vren.debug.vrenbrowsercore;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vren.vrenbrowsercore.HTTP;
import vren.vrenbrowsercore.HTTP.Sink.*;

import java.util.*;

public class HTTPClientDebug extends Application{
    public static boolean STOP = false;
    public static HTTP http = new HTTP();
    public static void main(String[] args){
        new Thread(Application::launch).start();
        http.sink.replace(
                "https://www.google.com/",
                List.of(new Header()
                                .setType("Accept")
                                .setValue("/index.html"),
                        new Header()
                                .setType("User-Agent")
                                .setValue("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                ),
                (short) 443);
        while(!STOP){
            printLastReturn();
            try{Thread.sleep(1000);}catch(InterruptedException e){throw new RuntimeException("Exception: " + e);}
        }
    }
    public static void printLastReturn(){
        System.out.println(http.getOut().headers.toString());
        System.out.println(http.getOut().msg);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Label Text = new Label("Click to Stop");
        Button button = new Button("Here");
        button.setOnAction(event -> STOP = true);
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(Text, button);
        Scene scene = new Scene(vBox, 400, 400);
        primaryStage.setTitle("Vren");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
