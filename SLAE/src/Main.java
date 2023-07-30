
public class Main {
    public static void main(String[] args) {

        int length = 0;
        double det = 1;

        length = Start.getLength(length);

        double[][] matrix = new double[length][length];
        double[] vector = new double[length];
        double[] answer = new double[length];

        Start.getMethod(matrix, vector, length);

        System.out.println("Ваша матрица:");
        Enter.IO.printMatrix(matrix, vector, length);

        Gauss.gaussianElimination(matrix, vector, answer, length, det);
        if(!Gauss.doesSolutionExist) {
            System.out.println(Gauss.errorMessage);
            System.exit(0);
        } else {

            System.out.println("Треугольная матрица:");
            Enter.IO.printMatrix(matrix, vector, length);

            det = MatrixManipulations.Determinant.findDeterminant(matrix, length, det);
            System.out.println("Определитель системы уравнений:\ndet = " + det + "\n");

            System.out.println("Решение системы уравнений:");
            Enter.IO.printVector(answer, length);

            System.out.println("\nВектор невязок:");
            MatrixManipulations.Residual.residual(matrix, vector, answer, length);
        }
    }
}
