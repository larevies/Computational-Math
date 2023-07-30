package Enter;

import java.util.Scanner;

public class IO {

    public static void scan(double[][] matrix, double[] vector, int length, Scanner input) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length + 1; j++) {
                if (j == length) {
                    vector[i] = input.nextDouble();
                } else {
                    matrix[i][j] = input.nextDouble();
                }
            }
        }
    }

    public static void printMatrix(double[][] matrix, double[] vector, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(" | " + vector[i]);
        }
        System.out.println();
    }

    public static void printVector(double[] vector, int length) {
        for (int i = 0; i < length; i++) {
            if (i != length - 1) {
                System.out.print("x" + (i+1) + " = " + vector[i] + ", ");
            } else {
                System.out.print("x" + (i+1) + " = " + vector[i]);
            }
        }
        System.out.println();
    }
}
