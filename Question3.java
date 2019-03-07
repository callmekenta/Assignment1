package sample;

//import all the libraries
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.geometry.Point2D;


public class Main extends Application {

    //initialize variables
    private double paneWidth = 700;//width of the pane
    private double paneHeight = 600;//length
    double CircleRadius = 200;// radius of the circle
    Circle Circle = new Circle(//inbuilt shape constructor for the main circle to be displayed
            paneWidth / 2, paneHeight / 2, CircleRadius);


    //three smaller circles to supplement as point on the main circle, as well as their starting position based on the radius of the original circle
    private Circle[] point = {

            new Circle((paneWidth/2)-CircleRadius, (paneHeight/2), 5),
            new Circle((paneWidth/2)+CircleRadius, (paneHeight/2), 5),
            new Circle((paneWidth/2), (paneHeight/2)-CircleRadius, 5)
    };

    // creating the lines that will be used as vectors to compute the angles within them
    // the text that will display each of the three angles
    private Text[] text = {new Text(), new Text(), new Text()};
    private Line[] line = {new Line(), new Line(), new Line()};

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        Pane pane = new Pane();//create the pane
        setLines();//call the set lines function to calculate the initial angles and display them
        Circle.setFill(Color.WHITE);// fill the main shape with the colour white
        Circle.setStroke(Color.BLACK);//make the edge of the circle black

        //fill the small circles supplementing as point with the color black
        point[0].setFill(Color.BLACK);
        point[1].setFill(Color.BLACK);
        point[2].setFill(Color.BLACK);

        //place the points and the text displaying angles in the scene
        pane.getChildren().addAll(Circle,
                text[0], text[1], text[2], point[0], point[1], point[2], line[0], line[1], line[2]);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, paneWidth, paneHeight);
        primaryStage.setTitle("Angles"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        //computing the point0 being dragged
        point[0].setOnMouseDragged((MouseEvent e) -> {

            //binding the point to the shape of the circle so it cannot move anywhere else in the scene using point 2D function
            Point2D p0 = new Point2D(Circle.getCenterX(), Circle.getCenterY());
            Point2D mouse = new Point2D(e.getX(), e.getY());
            Point2D centerToMouse = mouse.subtract(p0);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(Circle.getRadius());
            Point2D newPoint = centerToNewPoint.add(p0);

            // recalling the set lines function to recompute the angle as a point is moved and the length of the line changes, as does the angle between them
            if (point[0].contains(e.getX(), e.getY())) {
                point[0].setCenterX(newPoint.getX());
                point[0].setCenterY(newPoint.getY());
                setLines();
            }
        });

        //computing the point2 being dragged
        point[1].setOnMouseDragged((MouseEvent e) -> {

            //binding the point to the shape of the circle so it cannot move anywhere else in the scene using point 2D function
            Point2D p1 = new Point2D(Circle.getCenterX(), Circle.getCenterY());
            Point2D mouse = new Point2D(e.getX(), e.getY());
            Point2D centerToMouse = mouse.subtract(p1);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(Circle.getRadius());
            Point2D newPoint = centerToNewPoint.add(p1);

            // recalling the set lines function to recompute the angle as a point is moved and the length of the line changes, as does the angle between them
            if (point[1].contains(e.getX(), e.getY())) {
                point[1].setCenterX(newPoint.getX());
                point[1].setCenterY(newPoint.getY());
                setLines();
            }
        });

        //computing the point2 being dragged
        point[2].setOnMouseDragged((MouseEvent e) -> {

            //binding the point to the shape of the circle so it cannot move anywhere else in the scene using point 2D function
            Point2D p2 = new Point2D(Circle.getCenterX(), Circle.getCenterY());
            Point2D mouse = new Point2D(e.getX(), e.getY());
            Point2D centerToMouse = mouse.subtract(p2);
            Point2D centerToNewPoint = centerToMouse.normalize().multiply(Circle.getRadius());
            Point2D newPoint = centerToNewPoint.add(p2);

            // recalling the set lines function to recompute the angle as a point is moved and the length of the line changes, as does the angle between them
            if (point[2].contains(e.getX(), e.getY())) {
                point[2].setCenterX(newPoint.getX());
                point[2].setCenterY(newPoint.getY());
                setLines();
            }
        });
    }

    //the setlines functions that gets the length of the line between two points from the center of 1 point to the center of the adjacent point
    private void setLines() {

        line[0].setStartX(point[0].getCenterX());//line0 start position horizontal at center of point0
        line[0].setStartY(point[0].getCenterY());//line0 start position vertical at center of point0
        line[0].setEndX(point[1].getCenterX());//line0 end position horizontal at center of point1
        line[0].setEndY(point[1].getCenterY());//line0 start position vertical at center of point0

        line[1].setStartX(point[0].getCenterX());
        line[1].setStartY(point[0].getCenterY());
        line[1].setEndX(point[2].getCenterX());
        line[1].setEndY(point[2].getCenterY());

        line[2].setStartX(point[1].getCenterX());
        line[2].setStartY(point[1].getCenterY());
        line[2].setEndX(point[2].getCenterX());
        line[2].setEndY(point[2].getCenterY());

        // Compute angles
        double a = new Point2D(point[2].getCenterX(), point[2].getCenterY()).
                distance(point[1].getCenterX(), point[1].getCenterY());//getting the length of line0 based on start and end position of both points
        double b = new Point2D(point[2].getCenterX(), point[2].getCenterY()).
                distance(point[0].getCenterX(), point[0].getCenterY());//getting the length of line1 based on start and end position of both points
        double c = new Point2D(point[1].getCenterX(), point[1].getCenterY()).
                distance(point[0].getCenterX(), point[0].getCenterY());//getting the length of line2 based on start and end position of both points
        double[] angle = new double[3];

        angle[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));// calculating the angle between two lines based on line length
        angle[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));// calculating the angle between two lines based on line length
        angle[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));// calculating the angle between two lines based on line length

        //updating text to display new angle as the angle changes
        for (int i = 0; i < 3; i++) {
            text[i].setX(point[i].getCenterX());
            text[i].setY(point[i].getCenterY() - 20);
            text[i].setText(String.format("%.2f", Math.toDegrees(angle[i])));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}