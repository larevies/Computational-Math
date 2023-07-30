package SystemOfEquations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FixedPointSystem {

    static String errorMessage = "Условие сходимости не выполнено, решение системы уравнений с заданными начальными " +
            "приближениями найти невозможно.";
    static List<Double> answer;
    static int errorCounter(List<Function<List<Double>, Double>> list, int len, double err) {
        int count = 0;
        for (int j = 0; j < len; j++) {
            if (Math.abs(list.get(j).apply(answer)) <= err) {
                count++;
            }
        }
        return count;
    }

    static Double[] toArray(List<Double> list, int len) {
        Double[] array = new Double[len];
        for(int i = 0; i < len; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    static List<Double> fixedPointIteration(List<Function<List<Double>, Double>> list, List<Double> x, int len,
                                            double err) {
        Double[] arrayX = toArray(x, len);

        while (Gauss.doesSolutionExist) {
            List<Double> fx = new ArrayList<>(len);
            List<Double> intermediateX = Arrays.asList(arrayX);

            for(int j = 0; j < len; j++) {
                x.set(j, intermediateX.get(j));
                fx.add(j, list.get(j).apply(intermediateX));
            }

            Double[][] dx = new Double[len][len];


            for (int j = 0; j < len; j++) {

                for (int h = 0; h < len; h++) {

                    if (h == 0) {
                        x.set(j, intermediateX.get(j) + 1e-8);
                    }
                    dx[h][j] = ((list.get(h).apply(x) - fx.get(h)) / 1e-8);
                    if (h == len - 1) {
                        x.set(j, intermediateX.get(j));
                    }
                }
            }


            Double[] arrayFx = toArray(fx, len);
            Double[] arrayAnswer = Gauss.gaussianElimination(dx, arrayFx, len);

            if (!Gauss.doesSolutionExist) {
                System.out.println(errorMessage);
                System.exit(0);
            }

            for (int h = 0; h < len; h++) {
                arrayX[h] = arrayX[h] - arrayAnswer[h];
            }

            answer = Arrays.asList(arrayX);

            int count = errorCounter(list, len, err);
            if (count == len) {
                return answer;
            }
        }

        System.out.println(errorMessage);
        System.exit(0);
        return answer;
    }
}
