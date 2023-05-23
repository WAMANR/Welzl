import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class PointDrawer extends JPanel {

    private ArrayList<Point> points;

    private Circle circle;

    private int offset = 200;

    public PointDrawer(ArrayList<Point> points, Circle circle) {
        this.points = points;
        this.circle = circle;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Set the background color
        setBackground(Color.WHITE);

        // Set the axis color
        g.setColor(Color.BLACK);

        // Draw the x-axis
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        // Draw the y-axis
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

        // Set the point color
        g.setColor(Color.BLUE);

        // Draw each point as a small shape
        for (Point point : points) {
            double x = point.GetX() + offset;
            double y = point.GetY() + offset;
            g2d.fill(new Ellipse2D.Double(x - 2.0, y - 2.0, 4.0, 4.0));
        }

        g.setColor(Color.RED);
        // Draw Circle
        int x = (int) Math.round(circle.GetCenter().GetX() - circle.GetRadius());
        int y = (int) Math.round(circle.GetCenter().GetY() - circle.GetRadius());
        int size = (int) Math.round(circle.GetRadius() * 2);
        g.drawOval(x + offset, y + offset, size, size);


    }
}
