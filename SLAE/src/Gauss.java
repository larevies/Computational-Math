public class Gauss {
    static boolean doesSolutionExist = true;
    static String errorMessage = "Решения не существует.";
    public static void mainElement(double[][] matrix, int length, int i, int max, double det) {
        for (int j = i + 1; j < length; j++) {
            if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) {
                max = j;
                det *= -1;
            }
        }
    }
    public static void swapMatrix(double[][] matrix, int max, int i) {
        double[] tempArray = matrix[i];
        matrix[i] = matrix[max];
        matrix[max] = tempArray;
    }
    public static void swapVector(double[] vector, int max, int i) {
        double tempDouble = vector[i];
        vector[i] = vector[max];
        vector[max] = tempDouble;
    }
    public static void triangleMatrix(double[][] matrix, double[] vector, int length, int i) {
        for (int j = i + 1; j < length; j++) {

            double ratio = -(matrix[j][i] / matrix[i][i]);
            vector[j] += ratio * vector[i];

            for (int h = i; h < length; h++) {

                matrix[j][h] += ratio * matrix[i][h];
            }
        }
    }
    public static void resolve(double[][] matrix, double[] vector, double[] answer, int length) {
        for (int i = length - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < length; j++) {
                sum += matrix[i][j] * answer[j];
            }
            answer[i] = (vector[i] - sum) / matrix[i][i];
        }
    }
    public static void gaussianElimination(double[][] matrix, double[] vector, double[] answer, int length,
                                           double det) {

        for (int i = 0; i < length; i++) {
            int max = i;
            mainElement(matrix, length, i, max, det);
            swapMatrix(matrix, max, i);
            swapVector(vector, max, i);
            if (matrix[i][i] == 0) {
                doesSolutionExist = false;
            } else {
                triangleMatrix(matrix, vector, length, i);
            }
        }
        resolve(matrix, vector, answer, length);
    }
}
