package org.example;

import java.util.List;
import java.util.function.Function;

public class Analytical {
    private static double defaultFunction(List<Double> args) {
        return 0.0;
    }
    private static double firstAnalytical(List<Double> args) {
        return Math.cos(args.get(0)) - Math.cos(args.get(2)) + args.get(1);
    }
    private static double secondAnalytical(List<Double> args) {
        return args.get(1) * Math.exp((Math.pow(args.get(2), 2) - Math.pow(args.get(0), 2))/4);
    }
    private static double thirdAnalytical(List<Double> args) {
        return Math.sqrt(2 * args.get(2) + (Math.pow(args.get(1), 2) - 1) * Math.exp(2 * args.get(2)) + 1);
    }
    private static double fourthAnalytical(List<Double> args) {
        return (args.get(0) + args.get(1) + 1) * Math.exp(args.get(2) - args.get(0)) - args.get(2) - 1;
    }
    public static Function<List<Double>, Double> getFunctions(int n) {
        return switch (n) {
            case (1) -> Analytical::firstAnalytical;
            case (2) -> Analytical::secondAnalytical;
            case (3) -> Analytical::thirdAnalytical;
            case (4) -> Analytical::fourthAnalytical;
            default -> Analytical::defaultFunction;
        };
    }
}
