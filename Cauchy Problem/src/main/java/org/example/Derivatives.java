package org.example;

import java.util.function.BiFunction;

import static java.lang.Math.sin;

public class Derivatives {
    private static double firstFunction(double x, double y) {
        return sin(x);
    }
    private static double secondFunction(double x, double y) {
        return (x * y)/2;
    }
    private static double thirdFunction(double x, double y) {
        return y - (2 * x)/y;
    }
    private static double fourthFunction(double x, double y) {return x + y;}
    private static double defaultFunction(double x, double y) {
        return 0.0;
    }
    public static BiFunction<Double, Double, Double> getFunctions(int n) {
        return switch (n) {
            case (1) -> Derivatives::firstFunction;
            case (2) -> Derivatives::secondFunction;
            case (3) -> Derivatives::thirdFunction;
            case (4) -> Derivatives::fourthFunction;
            default -> Derivatives::defaultFunction;
        };
    }
}
