package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("""
                Серебренникова Валерия P32202
                Лабораторная работа №4 "Аппроксимация и Интерполяция"
                Вариант "Метод интерполяции Лагранжа"
                
                Данная программа ищет значение заданной пользователем точки по заранее заданному набору точек
                и значений функции, пользуясь методом интерполяции Лагранжа.
                
                Вы можете задать точки для своей функции или для одной из указанных в программе.
                Если вы воспользуетесь заданными программой функциями, Вам будет выведена разница
                между полученным методом Лагранжа значением и реальным значением функции.
                
                Пожалуйста, введите соответствующую цифру:
                
                0. Задать точки для своей функции
                1. y = lg(x)
                2. y = exp(x)
                3. y = sin(x)
                4. y = 2x^3;
                5. y = x^2""");

        int number = IO.getInt(1);

        System.out.println("Пожалуйста, введите количество точек в задаваемом наборе данных:");
        int n = IO.getInt(0);

        System.out.println("Введите точки (значения функции на оси X):");
        List<Double> xAxis = IO.addToList(n, 0);
        List<Double> yAxis = new ArrayList<>();

        if (number == 0) {
            System.out.println("Введите соответствующие значения точек (значения функции на оси Y):");
            yAxis = IO.addToList(n, 1);

        } else {
            List<Function<Double, Double>> list = Functions.getFunction(number);

            for (int i = 0; i < n; i++) {
                yAxis.add(i, list.get(0).apply(xAxis.get(i)));

                if (list.get(0).apply(xAxis.get(i)).isNaN() || list.get(0).apply(xAxis.get(i)).isInfinite()) {
                    System.out.println("Значение функции в точке " + xAxis.get(i) + " не определено.");
                    System.exit(0);
                }
            }
            for (int i = 0; i < n; i++) {
                System.out.println("f(" + xAxis.get(i) + ") = " + yAxis.get(i));
            }
        }


        System.out.println("В какой точке вы хотите найти значение функции? Введите X:");
        double x = IO.getDouble();

        double y = Interpolation.LagrangeInterpolation(xAxis, yAxis, x);
        System.out.println("Найденное значение Y = " + y);
        if ((Functions.getFunction(number).get(0).apply(x)).isNaN() ||
                Functions.getFunction(number).get(0).apply(x).isInfinite()) {
            System.out.println("Верное значение функции не определено.");
        } else if (number != 0) {
            System.out.println("Разница между программно-высчитанным значением функции и интерполированным " +
                    "\nметодом Лагранжа значением функции = " +
                    Math.abs(y - Functions.getFunction(number).get(0).apply(x)));
        }
        xAxis.add(n, x);
        yAxis.add(n, y);
        Charts.draw(xAxis, yAxis, n + 1, number);

    }
}