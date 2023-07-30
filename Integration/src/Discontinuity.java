import java.util.function.Function;

public class Discontinuity {
    public static double check(double a, Function<Double, Double> f){
        if (f.apply(a).isNaN()) {
            if (!f.apply(a - 1e-8).isNaN() && !f.apply(a + 1e-8).isNaN()) {
                System.out.println("В точке " + a + " функция имеет разрыв первого рода." +
                        "\nРазрыв можно устранить, воспользовавшись значением функции слева от х," +
                        "\nгде f(х) = " + f.apply(a - 1e-8) + ",\nили справа," +
                        "\nгде f(x) = " + f.apply(a + 1e-8) + "." +
                        "\nПожалуйста, напишите 1, если выбираете значение слева, и 2, если выбираете значение справа.");
                int response = Input.get_int();
                if (response == 1) {
                    a = f.apply(a - 1e-8);
                    return a;
                } else if (response == 2) {
                    a = f.apply(a + 1e-8);
                    return a;
                } else {
                    System.out.println("Не выбрана ни одна из предложенных границ.");
                    System.exit(0);
                }
            } else if (!f.apply(a - 1e-8).isNaN() && !f.apply(a - 1e-8).isInfinite() && f.apply(a + 1e-8).isNaN()) {
                System.out.println("В точке " + a + " значение функции не определено, однако в максимально близкой к ней "
                        + "точке слева значение определено. Точка " + a + " будет заменена на " + (a - 1e-8) + ".");
                a = a - 1e-8;
                return a;
            } else if (f.apply(a - 1e-8).isNaN() && !f.apply(a + 1e-8).isNaN() && !f.apply(a + 1e-8).isInfinite()) {
                System.out.println("В точке " + a + " значение функции не определено, однако в максимально близкой к ней "
                        + "точке справа значение определено. Точка " + a + " будет заменена на " + (a + 1e-8) + ".");
                a = a + 1e-8;
                return a;
            } else {
                System.out.println("Точка разрыва второго рода, устранить невозможно.");
                System.exit(0);
            }
        } else if (f.apply(a).isInfinite()) {
            System.out.println("Точка разрыва второго рода, устранить невозможно.");
            System.exit(0);
        }
        return 0.0;
    }
}
