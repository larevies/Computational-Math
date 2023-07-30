import java.util.function.Function;

import static java.lang.Math.*;

public class Derivative {
    static double first(double x) {
        return 24 * x * pow(x, -6);
    }
    static double second(double x) {
        return 16 * pow(x, -5) * (x * cos(x) - sin(x)) - 40 * x * pow(x, -6) * (x * cos(x) - sin(x))
                - 8 * sin(x) * pow(x, 2) * pow(x, -5) + 2 * x * sin(x) * pow (x, -4) + x * sin(x) * pow(x, -2)
                + 4 * x * cos(x) * pow(x, -3) - 6 * x * sin(x) * pow(x, -4);
    }
    static double defaultDerivative(double x) {
        return 0.0;
    }
    public static Function<Double, Double> get_function(int n) {
        switch (n) {
            case 1: return Derivative::first;
            case 2: return Derivative::second;
            default: return Derivative::defaultDerivative;
        }
    }
}
