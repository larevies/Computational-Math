package org.example;

import java.util.List;
import java.util.function.Function;

public class Interpolation {
    static double x = 0.0, xi = 0, xj = 0;
    public static double function(double x) {
        return (x - xj) / (xi - xj);
    }
    public static List<Function<Double,Double>> getFunction () {
        return List.of(Interpolation::function);
    }
    public static double LagrangeInterpolation(List<Double> xAxis, List<Double> yAxis, double arg) {

        x = arg;
        int length = xAxis.size();
        double answer = 0.0;

        for (int i = 0; i < length; i++) {

            double L = 1.0;
            xi = xAxis.get(i);

            for (int j = 0; j < length; j++) {

                if (j != i) {

                    xj = xAxis.get(j);
                    L *= (getFunction().get(0).apply(x));
                }
            }

            answer += (L * yAxis.get(i));
        }

        return answer;
    }
}
