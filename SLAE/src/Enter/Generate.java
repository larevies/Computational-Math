package Enter;

import java.util.Random;

public class Generate {
    public static void generate(double[][] matrix, double[] vector, int length) {
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = random.nextDouble(-length, length);
            }
            vector[i] = random.nextDouble(-length, length);
        }
    }
}
