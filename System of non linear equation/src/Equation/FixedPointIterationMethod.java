package Equation;
import java.util.function.Function;

import static java.lang.Math.abs;

public class FixedPointIterationMethod {
    static double derive(double x, Function<Double, Double> list) {
        double h = 1e-8;
        return (list.apply(x + h) - list.apply(x)) / h;
    }
    public static double fixedPointIteration(double x0, double err) {

        while(FunctionsEquation.fixedPointIsPossible) {

            double x = FunctionsEquation.list.get(1).apply(x0);
            if (abs(x - x0) <= err){return x0;}

            Double x_ = derive(x, FunctionsEquation.list.get(1));
            if (abs(x_) >= 1 || x_.isNaN() || x_.isInfinite()) {
                FunctionsEquation.fixedPointIsPossible = false;
                return 0.0;
            }

            x0 = x;
        }

        return 0.0;
    }
}
