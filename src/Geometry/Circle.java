package Geometry;

import java.util.ArrayList;

public class Circle {
    private Point _center;
    private double _radius;

    public Circle(){
        SetCenter(new Point(0, 0));
        SetRadius(0);
    }

    public Circle(Point center, double radius){
        SetCenter(center);
        SetRadius(radius);
    }

    //Calcul du cercle entre deux points
    public Circle(Point a, Point b){
        Point center = Line.GetMiddle(a, b);
        double radius = Line.GetDistance(center, b);
        SetCenter(center);
        SetRadius(radius);
    }

    //Calcul du cercle circonscrit
    public Circle(Point a, Point b, Point c){
        double d = 2 * (a.GetX() * (b.GetY() - c.GetY()) + b.GetX() * (c.GetY() - a.GetY()) + c.GetX() * (a.GetY() - b.GetY()));
        double centerX = ((a.GetX() * a.GetX() + a.GetY() * a.GetY()) * (b.GetY() - c.GetY()) +
                          (b.GetX() * b.GetX() + b.GetY() * b.GetY()) * (c.GetY() - a.GetY()) +
                          (c.GetX() * c.GetX() + c.GetY() * c.GetY()) * (a.GetY() - b.GetY())) / d;
        double centerY = ((a.GetX() * a.GetX() + a.GetY() * a.GetY()) * (c.GetX() - b.GetX()) +
                          (b.GetX() * b.GetX() + b.GetY() * b.GetY()) * (a.GetX() - c.GetX()) +
                          (c.GetX() * c.GetX() + c.GetY() * c.GetY()) * (b.GetX() - a.GetX())) / d;

        Point center = new Point(centerX, centerY);

        double rayon = Line.GetDistance(a, center);

        SetCenter(center);
        SetRadius(rayon);
    }

    public Point GetCenter(){
        return _center;
    }

    public double GetRadius(){
        return _radius;
    }

    public double GetDiameter(){
        return _radius * 2;
    }

    public void SetCenter(Point center){
        _center = center;
    }

    public void SetRadius(double radius){
        _radius = radius;
    }

    public boolean IsInCircle(Point point){
        double dx = _center.GetX() - point.GetX();
        double dy = _center.GetY() - point.GetY();
        double distance = dx * dx + dy * dy;
        return distance <= _radius * _radius;
    }

    public boolean AreInCircle(ArrayList<Point> points){
        for (Point p : points
             ) {
            if(!IsInCircle(p)){
                return  false;
            }
        }
        return  true;
    }
}
