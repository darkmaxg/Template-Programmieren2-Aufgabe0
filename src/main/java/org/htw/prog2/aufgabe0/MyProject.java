package org.htw.prog2.aufgabe0;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.SwingWrapper;

import java.util.LinkedList;

public class MyProject {

    /**
     * Calculate root X of a value S according to babylonian algorithm, starting with
     * an initial estimate X(0):
     * <ol>
     *     <li>Estimate the error e(n): e(n)=(S-X(n-1)²)/(2*X(n-1))</li>
     *     <li>Calculate X(n): X(n)=X(n-1)+e(n)</li>
     * </ol>
     * Continue until the estimated error reaches the desired maximum error
     * @param value The value to calculate the root of
     * @param initial The initial value to start the calculation with
     * @param maxerror The maximum allowed error
     * @return An array containing the values of all iterations. The last value in the array is the final estimate.
     */
    public static double[] calculateBabylonianRoot(double value, double initial, double maxerror) {
        if(value == 0 || value < 0 || initial == 0.0){
            return new double[]{0.0};
        }
        if(maxerror < 0){
            maxerror *= -1;
        }
        LinkedList<Double> allIterations = new LinkedList<>();
        double e = initial;
        while((value - (e * e)) > maxerror || (value - (e * e)) < -maxerror){
            e = 0.5 * (e + value / e);
            allIterations.add(e);
        }
        double[] allIterationsArray = new double[allIterations.size()];
        for(int i = 0; i < allIterations.size(); i++){
            allIterationsArray[i] = allIterations.get(i);
        }
        return allIterationsArray;
    }

    public static void plotData(double[] values) {
        XYChart chart = new XYChart(500, 500);
        chart.addSeries("Data", values);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        plotData(calculateBabylonianRoot(74821, 5, 0.1));
    }
}
