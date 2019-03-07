package sample;

//import libraries
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;



public class Main extends Application {
    public void start(Stage primaryStage) {

        //new pane
        Pane pane = new HBox(10);
        pane.setPadding(new Insets(5, 5, 5, 5));

        //creating an array of integers 1-54 (referencing the 54 cards by name)
        ArrayList<Integer> images = new ArrayList<>();//calling array constructor
        for (int i = 1; i < 55; i++) {//for loop to add 54 integers to the empty array
            images.add(i);
        }

        //shuffling the items of the array using the inbuilt function
        java.util.Collections.shuffle(images);//

        //pulling the first three integers of the array that are now shuffled
        for (int i = 0; i < 3; i++) {

            //each integer corresponds to the image file and is used to complete the file url and then imageview displays the image on the pane
            pane.getChildren().add(new ImageView(new Image("file:///C:\\Users\\100459934\\Desktop\\Assig1\\Cards\\" + images.get(i)+ ".png" )));
        }

        //displaying the scene, setting the title
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}