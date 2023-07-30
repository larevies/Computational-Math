package org.example;

import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;

public class Functions {
    public static double firstFunction(double x) {
        return log10(x);
    }
    public static double secondFunction(double x) {
        return exp(x);
    }
    public static double thirdFunction(double x) {
        return sin(x);
    }
    public static double fourthFunction(double x) {
        return pow(2*x, 3);
    }
    public static double fifthFunction(double x) {
        return pow(x, 2);
    }
    public static double defaultFunction(double x) {
        return 0.0;
    }
    public static List<Function<Double,Double>> getFunction (int n) {
        switch (n) {
            case 1: return List.of(Functions::firstFunction);
            case 2: return List.of(Functions::secondFunction);
            case 3: return List.of(Functions::thirdFunction);
            case 4: return List.of(Functions::fourthFunction);
            case 5: return List.of(Functions::fifthFunction);
            default: return List.of(Functions::defaultFunction);
        }
    }
}
