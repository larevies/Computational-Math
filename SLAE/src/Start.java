import java.util.InputMismatchException;
import java.util.Scanner;

public class Start {
    static Scanner scanner = new Scanner(System.in);
    public static int getLength(int length){

        System.out.println("""
                Данная программа решает СЛАУ методом Гаусса с выбором главных элементов.
                Работу выполнила Серебренникова Валерия, учебная группа P32202.
                Пожалуйста, введите количество неизвестных в вашем уравнении:""");
        try {
            length = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Введенный элемент не является числом. Кажется, кто-то допустил ошибку.");
            System.exit(0);
        }
        scanner.nextLine();
        return length;
    }

    public static void getMethod(double[][] matrix, double[] vector, int length){
        System.out.println("""
                Пожалуйста, выберите метод для ввода коэффициентов линейного уравнения.
                Ответьте "1", чтобы коэффициенты были взяты из файла Equation.txt;
                Ответьте "2", чтобы ввести матрицу вручную;
                Ответьте "3", чтобы ваше уравнение было автоматически сгенерировано.""");

        String response = scanner.nextLine();

        switch (response) {
            case "1" -> Enter.File.file(matrix, vector, length);
            case "2" -> Enter.Manual.manual(matrix, vector, length);
            case "3" -> Enter.Generate.generate(matrix, vector, length);
            default -> {
                System.out.println("Вы не выбрали ни один из предложенных методов.");
                System.exit(0);
            }
        }
    }
}
