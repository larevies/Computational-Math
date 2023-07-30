public class Main {

    public static void main(String[] args) {
        System.out.println("""
                Лабораторная работа №3
                Серебренникова Валерия P32202
                Вариант: метод Симпсона
                
                Данная программа находит значение определённого интеграла методом Симпсона.
                Пожалуйста, введите левую границу интервала:""");
        double a = Input.get_double();
        System.out.println("Пожалуйста, введите правую границу интервала:");
        double b = Input.get_double();
        if (a > b) {
            double temp = b;
            b = a;
            a = temp;
            SimpsonsMethod.isNeg = true;
        }
        System.out.println("""
                Пожалуйста, введите номер уравнения, которое необходимо проинтегрировать.
                Ниже представлен список доступных уравнений:
                1. 1/x
                2. sin(x)/x
                3. x^2 + 2
                4. 2x + 2
                5. log(x)""");
        int num = Input.get_int();
        System.out.println("Пожалуйста, введите максимальную разницу между двумя разбиениями:");
        double eps = Input.get_double();

        double answer = SimpsonsMethod.integrate(a, b, num, eps);
        System.out.println("Найденное значение интеграла:");
        System.out.println(answer);
    }
}