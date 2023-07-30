package Enter;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class File {
    public static void file(double[][] matrix, double[] vector, int length) {
        try {
            Scanner input = new Scanner(new java.io.File("src/Equation.txt"));
            while (input.hasNext()) {
                IO.scan(matrix, vector, length, input);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла, на который ссылается данный метод, не существует. Пожалуйста, проверьте его " +
                    "наличие.");
            System.exit(0);
        } catch (NoSuchElementException e) {
            System.out.println("Ожидаемые элементы - числа. Пожалуйста, проверьте файл на наличие ошибок.");
            System.exit(0);
        }
    }
}
