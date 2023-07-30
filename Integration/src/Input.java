import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    static Scanner scanner = new Scanner(System.in);
    public static double get_double() {
        try {return scanner.nextDouble();}
        catch (InputMismatchException e) {System.out.println("Неверно введена граница интервала.");}
        return 0.0;
    }
    public static int get_int() {
        try {return scanner.nextInt();}
        catch (InputMismatchException e) {System.out.println("Неверно введен номер уравнения.");}
        return 0;
    }


}
