package MatrixManipulations;

public class Determinant {
    public static double findDeterminant(double[][] matrix, int length, double det) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    det *= matrix[i][j];
                }
            }
        }
        return det;
    }
}
