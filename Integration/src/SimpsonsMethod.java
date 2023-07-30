import java.util.function.Function;


public class SimpsonsMethod {
    static boolean rExists = false;
    public static boolean isNeg = false;
    static Function<Double, Double> d;
    static double r = 0.0, answer = 0.0;
    static double check_value(double x, Function<Double, Double> f) {
        if (f.apply(x).isNaN() || f.apply(x).isInfinite()) {
            x = Discontinuity.check(x, f);
            return x;
        }
        return x;
    }

    static double Simpsons_method (double a, double b, double c, double h, Function<Double, Double> f) {
        return ((h / 3) * (f.apply(a) + 4 * f.apply(c) + f.apply(b)));
    }

    public static double integrate(double a, double b, int n, double epsilon) {

        Function<Double, Double> f = Functions.get_function(n);

        if (n == 1 || n == 2) {
            rExists = true;
            d = Derivative.get_function(n);
        }

        a = check_value(a, f);
        b = check_value(b, f);

        int split = (int) ((b - a) / epsilon);
        double width = (b - a) / split;

        for (int i = 0; i < split; i++) {

            double a1 = a + (i*width);
            double b1 = a + (i*width) + width;
            double c = (b1 + a1)/2;

            a1 = check_value(a1, f);
            b1 = check_value(b1, f);
            c = check_value(c, f);
            answer += Simpsons_method(a1, b1, c, width/2, f);
            if ((rExists && n == 1) || (rExists && n == 2)) {
                r += ((double) -1 / 90) * Math.pow(width / 2, 5) * d.apply(c);
            }
        }
        if (isNeg) {
            return -1 * (answer + r);
        } else {
            return (answer + r);
        }
    }
}
