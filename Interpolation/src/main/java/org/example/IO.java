package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class IO {
    static Scanner scanner = new Scanner(System.in);
    static String mismatchMessage = "Число введено неверно.";
    static String arithmeticMessage = "Количество точек должно быть больше либо равно двум.";
    static String xMessage = "Все значения X долны быть различными.";
    public static int getInt(int m) {
        int n = 0;
        try {
            n = scanner.nextInt();
            if (m == 1 && (n < 1 || n > 6)) {throw new InputMismatchException();}
            if (n < 2 && m == 0) {throw new ArithmeticException();}
        } catch (InputMismatchException e) {
            System.out.println(mismatchMessage);
            System.exit(0);
        } catch (ArithmeticException e) {
            System.out.println(arithmeticMessage);
            System.exit(0);
        }
        return n;
    }
    public static double getDouble() {
        double n = 0.0;
        try {
            n = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println(mismatchMessage);
            System.exit(0);
        }
        return n;
    }
    public static List<Double> addToList(int n, int c) {
        List<Double> list = new ArrayList<>();
        try {
            for (int i = 0; i < n; i++) {
                double a = scanner.nextDouble();
                if (c == 0 && list.contains(a)) {throw new ArithmeticException();}
                list.add(i, a);
            }
        } catch (InputMismatchException e) {
            System.out.println(mismatchMessage);
            System.exit(0);
        } catch (ArithmeticException e) {
            System.out.println(xMessage);
            System.exit(0);
        }
        return list;
    }
}
