package SystemOfEquations;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import static java.lang.Math.*;

public class FunctionsSystem {

    private static double firstFunction(List<Double> args) {return Math.sin(args.get(0));}
    private static double secondFunction(List<Double> args) {
        return (args.get(0) * args.get(1)) / 2;
    }
    private static double thirdFunction(List<Double> args) {
        return pow(args.get(0), 2) * pow(args.get(1), 2) - 3 * pow(args.get(0), 3) - 6 * pow(args.get(1),
                        3) + 8;
    }
    private static double fourthFunction(List<Double> args) {
        return pow(args.get(0), 4) - 9 * args.get(1) + 2;
    }
    private static double fifthFunction(List<Double> args) {
        return args.get(0) + pow(args.get(0), 2) - 2 * args.get(1) * args.get(2) - 0.1;
    }
    private static double sixthFunction(List<Double> args) {
        return args.get(1) + pow(args.get(1), 2) + 3 * args.get(0) * args.get(2) + 0.2;
    }

    private static double seventhFunction(List<Double> args) {
        return args.get(2) + pow(args.get(2), 2) + 2 * args.get(0) * args.get(1) - 0.3;
    }
    private static double eighthFunction(List<Double> args) {
        return args.get(0) + args.get(1);
    }

    private static double ninthFunction(List<Double> args) {
        return args.get(0) + args.get(1) + 5;
    }
    private static double defaultFunction(List<Double> args) {
        return 0.0;
    }


    static List<Function<List<Double>, Double>> get_functions(int num) {
        switch (num) {
            case 1:
                System.out.println("""
                Ваша система нелинейных уравнений:
                sin(x) = 0,
                xy/2 = 0
                """);
                return List.of(FunctionsSystem::firstFunction, FunctionsSystem::secondFunction);
            case 2:
                System.out.println("""
                Ваша система нелинейных уравнений:
                x^2 * y^2 - 3x^3 - 6y^3 + 8 = 0,
                x^4 - 9y + 2 = 0
                """);
                return List.of(FunctionsSystem::thirdFunction, FunctionsSystem::fourthFunction);
            case 3:
                System.out.println("""
                Ваша система нелинейных уравнений:
                x + x^2 - 2yz - 0.1 = 0,
                y + y^2 + 3xz + 0.2 = 0,
                z + z^2 + 2xy - 0.3 = 0""");
                return List.of(FunctionsSystem::fifthFunction, FunctionsSystem::sixthFunction, FunctionsSystem::seventhFunction);
            case 4:
                System.out.println("""
                Ваша система нелинейных уравнений:
                x + y = 0,
                x + y + 5 = 0""");
                return List.of(FunctionsSystem::eighthFunction, FunctionsSystem::ninthFunction);
            default: return List.of(FunctionsSystem::defaultFunction);
        }
    }
    public static double get_system(int num) {
        int n = 2;
        if (num == 3) {
            n = 3;
        }
        Scanner scanner = new Scanner(System.in);
        List<Function<List<Double>, Double>> list = get_functions(num);
        List<Double> approximations = new ArrayList<>(n);
        System.out.println("Введите начальные приближения:");
        for (int i = 0; i < n; i++) {
            approximations.add(i, scanner.nextDouble());
        }
        System.out.println("err");
        double err = scanner.nextDouble();
        List<Double> answer = FixedPointSystem.fixedPointIteration(list, approximations, n, err);
        System.out.print("Ответ: ");
        for (int i = 0; i < n; i++) {
            System.out.print("x" + (i+1) + " = ");
            System.out.printf("%.5f", answer.get(i));
            System.out.print(" ");
        }
        return 0;
    }
}

