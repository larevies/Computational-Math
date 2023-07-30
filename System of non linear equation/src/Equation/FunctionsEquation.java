package Equation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import static java.lang.Math.*;

public class FunctionsEquation {
    static boolean bisectionIsPossible = true;
    static boolean fixedPointIsPossible = true;
    static Scanner scanner = new Scanner(System.in);
    private static double firstFunction(double arg) {
        return (pow(arg, 3) + 2 * arg - 1);
    }
    private static double secondFunction(double arg) {
        return sin(arg) + 0.1;
    }
    private static double thirdFunction(double arg) {return -5 / pow(arg, 2);}
    private static double fourthFunction(double arg) {return pow(arg, 3) - 3 * arg;}

    private static double firstFunctionFPI(double arg) {return (1 - pow(arg, 3))/2;}
    private static double secondFunctionFPI(double arg) {return Math.asin(-0.1);}
    private static double thirdFunctionFPI(double arg) {return sqrt(-5);}
    private static double fourthFunctionFPI(double arg) {return (pow(arg, 3)/3);}

    private static double defaultFunction(double arg) {return 0.0;}


    public static List<Function<Double, Double>> list = new ArrayList<>(2);

    static List<Function<Double, Double>> get_function(int num) {
        return switch (num) {
            case 1 -> List.of(FunctionsEquation::firstFunction, FunctionsEquation::firstFunctionFPI);
            case 2 -> List.of(FunctionsEquation::secondFunction, FunctionsEquation::secondFunctionFPI);
            case 3 -> List.of(FunctionsEquation::thirdFunction, FunctionsEquation::thirdFunctionFPI);
            case 4 -> List.of(FunctionsEquation::fourthFunction, FunctionsEquation::fourthFunctionFPI);
            default -> List.of(FunctionsEquation::defaultFunction);
        };
    }

    public static void manipulations(int num) {

        list = get_function(num);
        double x0 = 0;
        double err = 0;

        if (num == 1) {
            System.out.println("""
                    Ваше нелинейное уравнение:
                    x^3 + 2x - 1 = 0
                    """);
        } else if (num == 2) {
            System.out.println("""
                    Ваше нелинейное уравнение:
                    sin(x) + 0.1 = 0
                    """);
        } else if (num == 3) {
            System.out.println("""
                    Ваше нелинейное уравнение:
                    -5 / x^2 = 0
                    """);
        } else if (num == 4) {
            System.out.println("""
                    Ваше нелинейное уравнение:
                    x^3 - 3x = 0
                    """);
        }

        System.out.println("Введите границы интервала для метода деления пополам:");
        double a = BisectionMethod.getBorder();
        double b = BisectionMethod.getBorder();
        if (a > b) {
            double temp = a;
            a = b;
            b = temp;
        }
        BisectionMethod.checkInterval(a, b, list);

        System.out.println("Введите начальное приближение для метода простых итераций:");
        try {
            x0 = scanner.nextDouble();
        } catch(InputMismatchException e) {
            System.out.println("Начальное приближение должно быть числом.");
        }

        System.out.println("Введите погрешность для метода простых итераций; помните, что десятичный разделитель - " +
                                                                                                        "запятая:");
        try {
            err = scanner.nextDouble();
            if (err < 0) {
                throw new ArithmeticException();
            }
        } catch (InputMismatchException e) {
            System.out.println("Погрешность должна быть числом.");
        } catch (ArithmeticException e) {
            System.out.println("Погрешность должна быть положительным числом.");
        }

        double answer1 = 0;

        if (bisectionIsPossible) {
            answer1 = BisectionMethod.bisectionMethod(a, b, err, list);
            System.out.print("Ответ, полученный методом деления пополам: x = ");
            System.out.printf("%.5f", answer1);
            System.out.println();
        } else {
            System.out.println("Найти корень уравнения методом половинного деления невозможно.");
        }

        double answer2 = FixedPointIterationMethod.fixedPointIteration(x0, err);

        if (fixedPointIsPossible) {
            System.out.print("Ответ, полученный методом простых итераций: x = ");
            System.out.printf("%.5f", answer2);
            System.out.println();
        } else {
            System.out.println("Найти корень уравнения методом простых итераций невозможно - не удовлетворено " +
                    "условие сходимости.");
        }

        if (fixedPointIsPossible && bisectionIsPossible) {
            System.out.print("Разница между ответами, полученными в методах = ");
            System.out.printf("%.5f", abs(answer1 - answer2));
        }

        System.exit(0);

    }
}
