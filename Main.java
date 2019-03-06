import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.Arrays;
import javafx.scene.chart.BarChart;
import static java.lang.Character.toLowerCase;

public class Main extends Application {

    //@Override
    public void start(Stage primaryStage) throws Exception{
        // inital file to test of program works
        String fileName = "interesting.txt";

        // creates the histogram based on the input in the text file
        makeHistogram(primaryStage, fileName);
    }

    // Function for making the Histogram
    public void makeHistogram( Stage primaryStage, String fileName) throws Exception
    {
        // Creating vertical and horizontal boxes for easier formatting
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        vbox.setSpacing(10);

        // Textfield for the user input to view other files
        TextField user_enter = new TextField();

        // Button for the file viewing
        Button button1 = new Button("View");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        // Arrays for easier formatting for later
        char[] Characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int[] Counting = new int[26];

        // Constructing barchart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Alphabet Count");
        xAxis.setLabel("Alphabets");
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x","y", "z")));

        yAxis.setLabel("Count");

        // The buffered reader will read in the file
        BufferedReader reader = new BufferedReader(new FileReader("interesting.txt"));
        if (fileName == "") {
        // do nothing
        } else {
            reader = new BufferedReader(new FileReader(fileName));
        }

        // When you press this button it will read the file that you have "viewed"
        button1.setOnAction(e -> {
            String file_thing = user_enter.getText();
            ReadingFile(file_thing);
        });

        // Reads in the text file and checks each character and if the character is the same with the one in the for loop
        // then the counter goes up for that specific character in the other array

        String word;
        while ((word = reader.readLine()) != null) {
            for (int i = 0; i < word.length(); i++) {
                for (int j = 0; j < 26; j++) {
                    if (toLowerCase((word.charAt(i))) == Characters[j]) {
                        Counting[j]++;
                    }
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            System.out.print(Counting[i]);
        }
        reader.close();

        // Big copy paste to add data to the barchart we couldn't get the for loop to loop over it properly

        XYChart.Series<String, Number> barchart = new XYChart.Series<>();
        barchart.getData().add(new XYChart.Data<>("a", Counting[0]));
        barchart.getData().add(new XYChart.Data<>("b", Counting[1]));
        barchart.getData().add(new XYChart.Data<>("c", Counting[2]));
        barchart.getData().add(new XYChart.Data<>("d", Counting[3]));
        barchart.getData().add(new XYChart.Data<>("e", Counting[4]));
        barchart.getData().add(new XYChart.Data<>("f", Counting[5]));
        barchart.getData().add(new XYChart.Data<>("g", Counting[6]));
        barchart.getData().add(new XYChart.Data<>("h", Counting[7]));
        barchart.getData().add(new XYChart.Data<>("i", Counting[8]));
        barchart.getData().add(new XYChart.Data<>("j", Counting[9]));
        barchart.getData().add(new XYChart.Data<>("k", Counting[10]));
        barchart.getData().add(new XYChart.Data<>("l", Counting[11]));
        barchart.getData().add(new XYChart.Data<>("m", Counting[12]));
        barchart.getData().add(new XYChart.Data<>("n", Counting[13]));
        barchart.getData().add(new XYChart.Data<>("o", Counting[14]));
        barchart.getData().add(new XYChart.Data<>("p", Counting[15]));
        barchart.getData().add(new XYChart.Data<>("q", Counting[16]));
        barchart.getData().add(new XYChart.Data<>("r", Counting[17]));
        barchart.getData().add(new XYChart.Data<>("s", Counting[18]));
        barchart.getData().add(new XYChart.Data<>("t", Counting[19]));
        barchart.getData().add(new XYChart.Data<>("u", Counting[20]));
        barchart.getData().add(new XYChart.Data<>("v", Counting[21]));
        barchart.getData().add(new XYChart.Data<>("w", Counting[22]));
        barchart.getData().add(new XYChart.Data<>("x", Counting[23]));
        barchart.getData().add(new XYChart.Data<>("y", Counting[24]));
        barchart.getData().add(new XYChart.Data<>("z", Counting[25]));

        barChart.getData().addAll(barchart);

        vbox.getChildren().add(barChart);
        hbox.getChildren().add(user_enter);
        hbox.getChildren().add(button1);

        vbox.getChildren().add(hbox);
        Group root = new Group(vbox);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("Question 4 "); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    // This has all the error messages for when the file is not found etc.
    // This function is a function for the button that reads in the file
    public void ReadingFile(String fileName)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            try
            {
                Stage stage = new Stage();
                makeHistogram(stage,fileName);
            }
            catch(Exception e)
            {
                System.out.print("Error");
            }

        }
        catch(FileNotFoundException err) {
            System.out.println("ERROR - File not found");

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

