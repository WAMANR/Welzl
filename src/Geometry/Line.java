package Geometry;

public class Line {

    private Point _a;
    private Point _b;
    public Line(Point a, Point b){
        SetA(a);
        SetB(b);
    }
    public Point GetA() {
        return _a;
    }

    public Point GetB(){
        return _b;
    }

    public void SetA(Point a){
        _a = a;
    }

    public void SetB(Point b){
        _b = b;
    }

    public static double GetDistance(Point a, Point b){
        return Math.sqrt(Math.pow(a.GetX() - b.GetX(), 2) + Math.pow(a.GetY() - b.GetY(), 2));
    }

    public double GetDistance(){
        return GetDistance(_a, _b);
    }

    public static Point GetMiddle(Point a, Point b){
        double x = (a.GetX() + b.GetX()) / 2;
        double y = (a.GetY() + b.GetY()) / 2;
        return new Point(x, y);
    }

    public Point GetMiddle(){
        return GetMiddle(_a, _b);
    }
}
