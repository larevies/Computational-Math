package Equation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class BisectionMethod {
    static Scanner scanner = new Scanner(System.in);
    public static void checkInterval(double a, double b, List<Function<Double, Double>> list) {
        if (list.get(0).apply(a) * list.get(0).apply(b) >= 0) {
            FunctionsEquation.bisectionIsPossible = false;
        }
    }
    public static double getBorder() {
        try {
            int num = scanner.nextInt();
            return num;
        } catch (InputMismatchException e) {
            System.out.println("Граница должна быть цифрой.");
        }
        return 0;
    }
    public static double bisectionMethod(double a, double b, double err, List<Function<Double, Double>> list) {
        double x = (a + b)/2;
        if (b - a <= err) return x;
        if (list.get(0).apply(a) * list.get(0).apply(x) < 0) return bisectionMethod(a, x, err, list);
        else if(list.get(0).apply(a) * list.get(0).apply(x) > 0) return bisectionMethod(x, b, err, list);
        else return x;
    }
}
