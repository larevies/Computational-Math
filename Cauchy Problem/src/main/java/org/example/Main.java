package org.example;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("""
                Лабораторная работа №5 "Дифференциирование
                Серебренникова Валерия Владимировна P32202
                Данная программа решает задачу Коши методом Милна.
                
                Пожалуйста, введите номер уравнения, для которого будет найдено решение задачи:
                
                1. y' = sin(x)
                2. y' = (xy)/2
                3. y' = y - (2x)/y
                4. y' = x + y""");
        int f = IO.getInt();
        System.out.println("Введите допустимую ошибку:");
        BiFunction<Double, Double, Double> function = Derivatives.getFunctions(f);
        Function<List<Double>, Double> analyticalFunction = Analytical.getFunctions(f);
        double eps = IO.getDouble();
        System.out.println("Введите начальную точку:");
        double a = IO.getDouble();
        System.out.println("Введите значение функции в точке a = " + a);
        double fa = IO.getDouble();
        System.out.println("Введите значение по оси X точки, для которой надо найти значение функции:");
        double b = IO.getDouble();


        double answer = Milne.solve(function, eps, a, fa, b, analyticalFunction);
        if (Milne.isWrong) {
            System.out.println("Как минимум в одной из точек заданного интервала функция не определена. " +
                    "Решение не будет верным.");
            System.exit(0);
        }

        System.out.println("Найденное решение: \nf(" + b + ") = " + answer);
        double analyticalValue = analyticalFunction.apply(List.of(a, fa, b));
        System.out.println("Аналитически полученное решение: \nf(" + b + ") = " + analyticalValue);
        System.out.println("Разница найденного решения с аналитическим = " + Math.abs(answer - analyticalValue));


        Charts.draw(analyticalFunction, a, fa, b, answer);

    }
}
