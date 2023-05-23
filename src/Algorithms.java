import java.util.ArrayList;
import java.util.Random;

public class Algorithms {

    public static Circle MinCircle(ArrayList<Point> points){

        if(points == null || points.isEmpty()){
            return null;
        }
        else if(points.size() == 1){
            return new Circle(points.get(0), 0);
        }
        else if (points.size() == 2){
            return new Circle(points.get(0), points.get(1));
        }
        else{
            return new Circle(points.get(0), points.get(1), points.get(2));
        }
    }

    public static Circle Naive(ArrayList<Point> points){
        for (Point a : points
             ) {
            for (Point b : points){
                Circle circle = new Circle(a, b);
                if(circle.AreInCircle(points)){
                    return circle;
                }
            }
        }
        Circle result = new Circle(new Point(0,0), Double.MAX_VALUE);
        for (Point a : points){
            for (Point b : points){
                for (Point c : points){
                    Circle circle = new Circle(a, b, c);
                    if(circle.AreInCircle(points) && circle.GetRadius() < result.GetRadius()){
                        result = circle;
                    }
                }
            }
        }
        return result;
    }

    private static Point ChooseRandom(ArrayList<Point> points){
        Random random = new Random();
        return points.get(random.nextInt(points.size()));
    }

    private static ArrayList<Point> ListCopy(ArrayList<Point> points){
        ArrayList<Point> result = new ArrayList<>();
        for (Point p : points){
            result.add(p);
        }
        return result;
    }

    private static ArrayList<Point> ListCopyRemove(ArrayList<Point> points, Point point){
        ArrayList<Point> result = ListCopy(points);
        result.remove(point);
        return result;
    }

    private static ArrayList<Point> ListCopyAdd(ArrayList<Point> points, Point point){
        ArrayList<Point> result = ListCopy(points);
        result.add(point);
        return result;
    }



    public static Circle Welzl(ArrayList<Point> P, ArrayList<Point> R){

        Circle minCircle;
        if(P.isEmpty() || R.size() == 3) {
            minCircle = MinCircle(R);
        }
        else{
            Point randPoint = ChooseRandom(P);
            P.remove(randPoint);
            minCircle = Welzl(P, R);
            P.add(randPoint);
            if(minCircle == null || !minCircle.IsInCircle(randPoint)){
                P.remove(randPoint);
                R.add(randPoint);
                minCircle = Welzl(P, R);
                R.remove(randPoint);
                P.add(randPoint);
            }
        }
        return minCircle;
    }

    public static ArrayList<Point> RandomPoints (int count, int max){
        ArrayList<Point> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++){
            result.add(new Point(random.nextFloat() * max, random.nextFloat() * max));
        }
        return result;
    }

}
