package SystemOfEquations;

public class Gauss {
    static boolean doesSolutionExist = true;
    public static void mainElement(Double[][] matrix, int length, int i, int max) {
        for (int j = i + 1; j < length; j++) {
            if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) {
                max = j;
            }
        }
    }
    public static void swapMatrix(Double[][] matrix, int max, int i) {
        Double[] tempArray = matrix[i];
        matrix[i] = matrix[max];
        matrix[max] = tempArray;
    }
    public static void swapVector(Double[] vector, int max, int i) {
        Double tempDouble = vector[i];
        vector[i] = vector[max];
        vector[max] = tempDouble;
    }
    public static void triangleMatrix(Double[][] matrix, Double[] vector, int length, int i) {
        for (int j = i + 1; j < length; j++) {

            Double ratio = -(matrix[j][i] / matrix[i][i]);
            vector[j] += ratio * vector[i];

            for (int h = i; h < length; h++) {

                matrix[j][h] += ratio * matrix[i][h];
            }
        }
    }
    public static void resolve(Double[][] matrix, Double[] vector, Double[] answer, int length) {
        for (int i = length - 1; i >= 0; i--) {
            Double sum = 0.0;
            for (int j = i + 1; j < length; j++) {
                sum += matrix[i][j] * answer[j];
            }
            answer[i] = (vector[i] - sum) / matrix[i][i];
        }
    }
    public static Double[] gaussianElimination(Double[][] matrix, Double[] vector, int length) {
        Double[] answer = new Double[length];
        for (int i = 0; i < length; i++) {
            int max = i;
            mainElement(matrix, length, i, max);
            swapMatrix(matrix, max, i);
            swapVector(vector, max, i);
            if (matrix[i][i] == 0) {
                doesSolutionExist = false;
            } else {
                triangleMatrix(matrix, vector, length, i);
            }
        }
        resolve(matrix, vector, answer, length);
        return answer;
    }
}
