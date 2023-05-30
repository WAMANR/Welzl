package Classes;

import Geometry.*;
import Geometry.Point;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Display extends JPanel {

    private ArrayList<Point> points;

    private Circle circle;

    private int offset = 200;

    public Display(ArrayList<Point> points, Circle circle) {
        this.points = points;
        this.circle = circle;
    }

    private void drawBackground(){
        setBackground(Color.WHITE);
    }

    private void drawPoints(Graphics g, Graphics2D g2d){
        g.setColor(Color.BLUE);
        for (Point point : points) {
            double x = point.GetX() + offset;
            double y = point.GetY() + offset;
            g2d.fill(new Ellipse2D.Double(x - 2.0, y - 2.0, 4.0, 4.0));
        }
    }

    private void drawCircle(Graphics g){

        g.setColor(Color.RED);
        int x = (int) Math.round(circle.GetCenter().GetX() - circle.GetRadius());
        int y = (int) Math.round(circle.GetCenter().GetY() - circle.GetRadius());
        int size = (int) Math.round(circle.GetRadius() * 2);
        g.drawOval(x + offset, y + offset, size, size);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        drawBackground();
        
        drawPoints(g, g2d);

        drawCircle(g);

    }
}
