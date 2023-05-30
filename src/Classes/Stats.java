package Classes;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;

public class Stats {

    private ArrayList<Instance> _instances;

    private int _instancesCount;

    private Duration _averageDurationWelzl = Duration.ZERO;
    
    private Duration _averageDurationNaive = Duration.ZERO;
        
    private int _averageIterationWelzl = 0;
        
    private int _averageIterationNaive = 0;
        
    private double _averageRadiusWelzl = 0;
        
    private double _averageRadiusNaive = 0;

    private double _varianceIterationNaive = 0;
    
    private double _varianceIterationWelzl = 0;
    
    private double _varianceRadiusNaive = 0;
        
    private double _varianceRadiusWelzl = 0;
        
    private long _varianceDurationNaiveTmp = 0;
        
    private long _varianceDurationWelzlTmp = 0;

    private Duration _varianceDurationNaive = Duration.ZERO;

    private Duration _varianceDurationWelzl = Duration.ZERO;

    
    public Stats(ArrayList<Instance> instances){
        _instances = instances;
        _instancesCount = instances.size();
    }

    public void Start(){
        SetAverage();
        SetStandardDeviation();
    }


    public void Dashboard(){
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JFreeChart duration = DurationChart();
        JFreeChart iterations = IterationChart();
        JFreeChart radius = RadiusChart();

        JLabel averageDurationWeltz = new JLabel("Durée moyenne Weltz " + durationToSeconds(_averageDurationWelzl));
        JLabel averageIterationWeltz = new JLabel("Nombre d'itérations/appels moyen Welzl: " + _averageIterationWelzl);
        JLabel averageRadiusWeltz = new JLabel("Rayon moyen du cercle minimum Welzl: " + _averageRadiusWelzl);
        JLabel varianceDurationWelzl = new JLabel("Ecart-type de la durée Welzl: " + durationToSeconds(_varianceDurationWelzl));
        JLabel varianceIterationWelzl = new JLabel("Ecart-type du nombre d'itérations/appels Welzl: " + _varianceIterationWelzl);
        JLabel varianceRadiusWelzl = new JLabel("Ecart-type du rayon du cercle minimum Welzl: " + _varianceRadiusWelzl);


        JLabel averageDurationNaive = new JLabel("Durée moyenne Naive " + durationToSeconds(_averageDurationNaive));
        JLabel averageIterationNaive = new JLabel("Nombre d'itérations/appels moyen Naive: " + _averageIterationNaive);
        JLabel averageRadiusNaive = new JLabel("Rayon moyen du cercle minimum Naive: " + _averageRadiusNaive);
        JLabel varianceDurationNaive = new JLabel("Ecart-type de la durée Naive: " + durationToSeconds(_varianceDurationNaive));
        JLabel varianceIterationNaive = new JLabel("Ecart-type du nombre d'itérations/appels Naive: " + _varianceIterationNaive);
        JLabel varianceRadiusNaive = new JLabel("Ecart-type du rayon du cercle minimum Naive: " + _varianceRadiusNaive);

        JPanel naivePanel = new JPanel();
        naivePanel.setLayout(new GridLayout(6, 1));
        JPanel welzlPanel = new JPanel();
        welzlPanel.setLayout(new GridLayout(6, 1));

        welzlPanel.add(averageDurationWeltz);
        welzlPanel.add(averageIterationWeltz);
        welzlPanel.add(averageRadiusWeltz);
        welzlPanel.add(varianceDurationWelzl);
        welzlPanel.add(varianceIterationWelzl);
        welzlPanel.add(varianceRadiusWelzl);

        naivePanel.add(averageDurationNaive);
        naivePanel.add(averageIterationNaive);
        naivePanel.add(averageRadiusNaive);
        naivePanel.add(varianceDurationNaive);
        naivePanel.add(varianceIterationNaive);
        naivePanel.add(varianceRadiusNaive);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3));
        panel.add(naivePanel);
        panel.add(welzlPanel);
        panel.add(new ChartPanel(duration));
        panel.add(new ChartPanel(iterations));
        panel.add(new ChartPanel(radius));





        // Ajoute le conteneur au centre de la fenêtre du dashboard
        frame.add(panel, BorderLayout.CENTER);

        // Affiche la fenêtre du dashboard
        frame.pack();
        frame.setVisible(true);

    }
    private JFreeChart DurationChart(){
        int i = 1;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(Instance instance : _instances){
            dataset.addValue(durationToSeconds(instance.GetDurationWelzl()), "Welzl", String.valueOf(i));
            dataset.addValue(durationToSeconds(instance.GetDurationNaive()), "Naive", String.valueOf(i));
            i++;
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Durations",
                "Instances",
                "Temps d'exécution en secondes",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        return chart;
    }

    private JFreeChart IterationChart() {
        int i = 1;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Instance instance : _instances) {
            dataset.addValue(instance.GetIterationWelzl(), "Welzl", String.valueOf(i));
            dataset.addValue(instance.GetIterationNaive(), "Naive", String.valueOf(i));
            i++;
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre d'appels/Itérations",
                "Instances",
                "Nombre d'appels ou d'itérations",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        return chart;
    }

    private JFreeChart RadiusChart(){
        int i = 1;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Instance instance : _instances) {
            dataset.addValue(instance.GetWelzlCircleRadius(), "Welzl", String.valueOf(i));
            dataset.addValue(instance.GetNaiveCircleRadius(), "Naive", String.valueOf(i));
            i++;
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Rayon du cercle minimum",
                "Instances",
                "Rayon du cercle",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        return chart;
    }

    private void SetAverage(){
        for (Instance instance : _instances){
            _averageDurationNaive = _averageDurationNaive.plus(instance.GetDurationNaive());
            _averageDurationWelzl = _averageDurationWelzl.plus(instance.GetDurationWelzl());
            _averageIterationNaive += instance.GetIterationNaive();
            _averageIterationWelzl += instance.GetIterationWelzl();
            _averageRadiusNaive += instance.GetNaiveCircleRadius();
            _averageRadiusWelzl += instance.GetWelzlCircleRadius();
        }
        _averageDurationNaive = _averageDurationNaive.dividedBy(_instancesCount);
        _averageDurationWelzl = _averageDurationWelzl.dividedBy(_instancesCount);
        _averageIterationNaive /= _instancesCount;
        _averageIterationWelzl /= _instancesCount;
        _averageRadiusNaive /= _instancesCount;
        _averageRadiusWelzl /= _instancesCount;

    }

    private void SetStandardDeviation(){
        for (Instance instance : _instances){
            _varianceIterationNaive += Math.pow(instance.GetIterationNaive() - _averageIterationNaive, 2);
            _varianceIterationWelzl += Math.pow(instance.GetIterationWelzl() - _averageIterationWelzl, 2);
            _varianceRadiusNaive += Math.pow(instance.GetNaiveCircleRadius() - _averageRadiusNaive, 2);
            _varianceRadiusWelzl += Math.pow(instance.GetWelzlCircleRadius() - _averageRadiusWelzl, 2);
            _varianceDurationNaiveTmp += Math.pow(instance.GetDurationNaive().getNano() - _averageDurationNaive.getNano(), 2);
            _varianceDurationWelzlTmp += Math.pow(instance.GetDurationWelzl().getNano() - _averageDurationWelzl.getNano(), 2);
        }
        _varianceIterationNaive = Math.sqrt(_varianceIterationNaive/_instancesCount);
        _varianceIterationWelzl = Math.sqrt(_varianceIterationWelzl/_instancesCount);
        _varianceRadiusNaive = Math.sqrt(_varianceRadiusNaive/_instancesCount);
        _varianceRadiusWelzl = Math.sqrt(_varianceRadiusWelzl/_instancesCount);
        _varianceDurationNaiveTmp = (long) Math.sqrt(_varianceDurationNaiveTmp/_instancesCount);
        _varianceDurationWelzlTmp = (long) Math.sqrt(_varianceDurationWelzlTmp/_instancesCount);
        _varianceDurationNaive = Duration.ofNanos(_varianceDurationNaiveTmp);
        _varianceDurationWelzl = Duration.ofNanos(_varianceDurationWelzlTmp);
    }

    private double durationToSeconds(Duration duration){
        int nano = duration.getNano();
        return (double) nano / 1_000_000_000;
    }
}
