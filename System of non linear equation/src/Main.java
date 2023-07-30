import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("""
            Серебренникова Валерия 32202
            Вы хотите решить нелинейное уравнение или систему нелинейных уравнений?
            1 - нелинейное уравнение
            2 - система нелинейных уравнений
            Введите выбранный номер:""");
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                System.out.println("""
            Пожалуйста, выберите нелинейное уравнение.
            1: x^3 + 2x - 1 = 0
            2: sin(x) + 0.1 = 0
            3. -5 / x^2 = 0
            4. x^3 - 3x = 0""");
                Start.getNumEquation();
            case 2:
                System.out.println("""
            Пожалуйста, выберите систему нелинейных уравнений.
            1. sin(x) = 0,
               xy/2 = 0
               
            2. x^2 * y^2 - 3x^3 - 6y^3 + 8 = 0,
               x^4 - 9y + 2 = 0
               
            3. x + x^2 - 2yz - 0.1 = 0,
               y + y^2 + 3xz + 0.2 = 0,
               z + z^2 + 2xy - 0.3 = 0
               
            4. x + y = 0,
               x + y + 5 = 0""");
                Start.getNumSystem();
            default: System.exit(0);
        }
    }
}