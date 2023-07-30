package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {
    static Scanner scanner = new Scanner(System.in);
    public static int getInt() {
        int n;
        try {
            n = scanner.nextInt();
            if (n < 1 || n > 4) {throw new InputMismatchException();}
            return n;
        } catch (InputMismatchException e) {
            System.out.println("Номер уравнения должен быть целым числом от 1 до 4.");
        }
        return 0;
    }
    public static double getDouble() {
        double n;
        try {
            n = scanner.nextDouble();
            return n;
        } catch (InputMismatchException e) {
            System.out.println("Ошибка должна быть числом..");
        }
        return 0.0;
    }
}
