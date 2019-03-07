package sample;

//import all the libraries
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

    //calling inbuilt textfield constructor to initialize for text fields, 3 for input and 1 for output
    private TextField tfInvestmentAmount = new TextField();
    private TextField tfNumberOfYears = new TextField();
    private TextField tfAnnualInterestRate = new TextField();
    private TextField tfFutureValue = new TextField();
    private Button btCalculate = new Button("Calculate");//calling inbuilt constructor to construct the calculate button

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create UI
        //set textfield and label placement on the grid
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("InvestmentAmount:"), 0, 0);
        gridPane.add(tfInvestmentAmount, 1, 0);
        gridPane.add(new Label("Number of Years:"), 0, 1);
        gridPane.add(tfNumberOfYears, 1, 1);
        gridPane.add(new Label("Annual Interest Rate:"), 0, 2);
        gridPane.add(tfAnnualInterestRate, 1, 2);
        gridPane.add(new Label("Future Value:"), 0, 3);
        gridPane.add(tfFutureValue, 1, 3);
        gridPane.add(btCalculate, 1, 4);

        // Set properties for UI
        gridPane.setAlignment(Pos.CENTER);
        tfInvestmentAmount.setAlignment(Pos.BOTTOM_RIGHT);
        tfNumberOfYears.setAlignment(Pos.BOTTOM_RIGHT);
        tfAnnualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
        tfFutureValue.setAlignment(Pos.BOTTOM_RIGHT);
        tfFutureValue.setEditable(false);
        GridPane.setHalignment(btCalculate, HPos.RIGHT);

        // process the click of the calculate button
        // receive the input from the initial amount, years/ and rate text fields
        btCalculate.setOnAction(e ->
        {
            double amount = Integer.parseInt(tfInvestmentAmount.getText());
            double years = Integer.parseInt(tfNumberOfYears.getText());
            double rate = Double.parseDouble(tfAnnualInterestRate.getText());
            double value = (amount * Math.pow((1 + (rate/1200)), (years*12)));// calculating the future value based on the inputs
            tfFutureValue.setText(String.format("$%.2f", value));//displaying the result in the future value text field by converting the value to string
        });
        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 300, 175);
        primaryStage.setTitle("Investment Calculator"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
