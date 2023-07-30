package Enter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Manual {
    public static void manual(double[][] matrix, double[] vector, int length) {
        System.out.println("Пожалуйста, введите коэффициенты ваших уравнений:");
        try {
            Scanner input = new Scanner(System.in);
            IO.scan(matrix, vector, length, input);
        } catch (InputMismatchException e) {
            System.out.println("Введенный элемент не является числом. Допущена ошибка.");
            System.exit(0);
        }
    }
}
