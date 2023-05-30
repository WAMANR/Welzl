package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import Geometry.*;

public class Instance {

    private String _filename;

    private int _instancePointsCount;

    private ArrayList<Point> _points;

    private Circle _circleNaive;

    private Circle _circleWelzl;

    private int _iterationsNaive [] = { 0 };

    private int _iterationsWelzl [] = { 0 };

    private Duration _durationNaive;
    
    private Duration _durationWelzl;

    public Instance(String filename, int instancePointsCount) {

        _filename = filename;
        _instancePointsCount = instancePointsCount;
    }

    public Duration GetDurationNaive(){
        return _durationNaive;
    }

    public Duration GetDurationWelzl(){
        return _durationWelzl;
    }

    public int GetIterationNaive(){
        return _iterationsNaive[0];
    }

    public int GetIterationWelzl(){
        return _iterationsWelzl[0];
    }

    public double GetNaiveCircleRadius(){
        return _circleNaive.GetRadius();
    }

    public double GetWelzlCircleRadius(){
        return _circleWelzl.GetRadius();
    }


    public void Init(){
        loadPoints();
        _durationNaive = Duration.ZERO;
        _durationWelzl = Duration.ZERO;
    }

    public void Start(){
        LocalTime depart = LocalTime.now();

        _circleWelzl = Algorithms.Welzl(_points, new ArrayList<Point>(),_iterationsWelzl);
        _durationWelzl = Duration.between(depart, LocalTime.now());
        

        depart = LocalTime.now();
        _circleNaive = Algorithms.Naive(_points, _iterationsNaive);
        _durationNaive = Duration.between(depart, LocalTime.now());
        
    }

    private void loadPoints() {
        System.out.println(_filename);
        ArrayList<Point> points = new ArrayList<Point>();

        try (BufferedReader reader = new BufferedReader(new FileReader(_filename))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < _instancePointsCount) {

                String[] coordinates = line.split(" ");

                if (coordinates.length == 2) {
                    int x = Integer.parseInt(coordinates[0]);
                    int y = Integer.parseInt(coordinates[1]);

                    points.add(new Point(x, y));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        _points = points;
    }
}
