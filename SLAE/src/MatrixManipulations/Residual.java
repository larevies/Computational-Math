package MatrixManipulations;

public class Residual {
    public static void residual(double[][] matrix, double[] vector, double[] answer, int length) {

        double[] residuals = new double[length];

        double index = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                index += (matrix[i][j] * answer[j]);
            }
            residuals[i] = vector[i] - index;
            index = 0;
        }
        Enter.IO.printVector(residuals, length);
    }
}
