import Equation.FunctionsEquation;
import SystemOfEquations.FunctionsSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Start {
    static int number;
    static String errorMessage = "Номер должен быть целым числом.";
    static Scanner scanner = new Scanner(System.in);
    static void casesEquation(int num) {
        switch (num) {
            case 1 -> FunctionsEquation.manipulations(1);
            case 2 -> FunctionsEquation.manipulations(2);
            case 3 -> FunctionsEquation.manipulations(3);
            case 4 -> FunctionsEquation.manipulations(4);
            default -> getNumEquation();
        }
    }
    static void casesSystem(int num) {
        switch (num) {
            case 1 -> FunctionsSystem.get_system(1);
            case 2 -> FunctionsSystem.get_system(2);
            case 3 -> FunctionsSystem.get_system(3);
            case 4 -> FunctionsSystem.get_system(4);
            default -> getNumSystem();
        }
    }
    static void getNumEquation() {
        try {
            System.out.println("Необходимо ввести номер уравнения - целое число от 1 до 3:");
            number = scanner.nextInt();
            casesEquation(number);
        } catch (InputMismatchException e) {
            System.out.println(errorMessage);
        }
    }
    static void getNumSystem() {
        try {
            System.out.println("Необходимо ввести номер системы уравнений - целое число от 1 до 3:");
            number = scanner.nextInt();
            casesSystem(number);
        } catch (InputMismatchException e) {
            System.out.println(errorMessage);
        }
    }
}
