package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Milne {
    static double h = 0.0;
    static String errorMessage = "С заданной точностью получить решение невозможно.";
    static boolean isWrong = false;
    static List<Double> ys = new ArrayList<>();
    static List<Double> xs = new ArrayList<>();
    public static double predict(double y_1, double y_2, double y_3, double y4) {
        return y4 + ((4*h)/3)*(2*y_1 - y_2 + 2*y_3);
    }

    public static double correct(double y_, double y_1, double y_2, double y2) {
        return y2 + (h/3)*(y_ + 4*y_1 + y_2);
    }

    public static double runge_kutta(BiFunction<Double, Double, Double> f, double xi, double yi) {
        double k1 = h * f.apply(xi, yi);
        double k2 = h * f.apply((xi + (h/2)), (yi + (k1/2)));
        double k3 = h * f.apply((xi + (h/2)), (yi + (k2/2)));
        double k4 = h * f.apply((xi + h), (yi + k3));
        double delYi = (((double) 1 /6) * (k1 + 2*k2 + 2*k3 + k4));
        return yi + delYi;
    }

    public static void check(Function<List<Double>, Double> f, int i, double a, double fa) {
        if (f.apply(List.of(a, fa, xs.get(i))).isNaN() ||
                f.apply(List.of(a, fa, xs.get(i))).isInfinite()) {
            isWrong = true;
        }
    }

    public static double solve(BiFunction<Double, Double, Double> f, double epsilon, double a, double fa,
                               double b, Function<List<Double>, Double> analytical) {

        h = (b - a)/100;

        xs.add(0, a);
        ys.add(0, fa);
        check(analytical, 0, a, fa);

        for (int i = 1; i < 4; i++) {
            xs.add(i, a + i * h);
            ys.add(i, runge_kutta(f, xs.get(i-1), ys.get(i-1)));
            check(analytical, i, a, fa);
        }

        for (int i = 4; i <= 100; i++) {

            xs.add(i, a + i*h);

            double y_1 = f.apply(xs.get(i - 1), ys.get(i - 1));
            double y_2 = f.apply(xs.get(i - 2), ys.get(i - 2));
            double y_3 = f.apply(xs.get(i - 3), ys.get(i - 3));

            double y2 = ys.get(i - 2);
            double y4 = ys.get(i - 4);

            double y_predicted = predict(y_1, y_2, y_3, y4);
            double y_ = f.apply(xs.get(i), y_predicted);
            double y_corrected = correct(y_, y_1, y_2, y2);


            boolean isFound = false;
            while (!isFound) {
                double eps =  Math.abs((y_corrected - y_predicted)/29);
                if (eps < epsilon) {isFound = true;} else {
                    y_ = f.apply(xs.get(i), y_corrected);
                    y_corrected = correct(y_, y_1, y_2, y2);
                    double eps_new = Math.abs((y_corrected - y_predicted)/29);
                    if (eps_new == eps) {
                        System.out.println(errorMessage);
                        System.exit(0);
                    }
                }
            }
            ys.add(i, y_corrected);
            check(analytical, i, a, fa);
        }
        return ys.get(100);
    }
}

