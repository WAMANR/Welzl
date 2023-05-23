import javax.swing.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Duration;

public class Main {

    public static void main(String[] args) {

        ArrayList<Point> points = Algorithms.RandomPoints(2000, 500);
        LocalDateTime depart = LocalDateTime.now();
        

        
        Circle circle = Algorithms.Naive(points);
        System.out.println("Naive : " +Duration.between(depart, LocalDateTime.now()));

        depart = LocalDateTime.now();
        circle = Algorithms.Welzl(points, new ArrayList<Point>());
        System.out.println("Welzl : " +Duration.between(depart, LocalDateTime.now()));


        // Create a JFrame to display the coordinate system and points
        JFrame frame = new JFrame("Point Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);

        // Create a PointDrawer object and add it to the JFrame
        PointDrawer pointDrawer = new PointDrawer(points, circle);
        frame.add(pointDrawer);

        // Display the JFrame
        frame.setVisible(true);
    }
}