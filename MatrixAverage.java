import java.util.Arrays;

public class MatrixAverage {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
        };
        
        int[] testValues = {1, 2};
        for (int n : testValues) {
            System.out.println("\nСреднее арифметическое для n=" + n + ":");
            int[][] result = applyAverageFilter(matrix, n);
            printMatrix(result);
        }
    }

    public static int[][] applyAverageFilter(int[][] matrix, int n) {
        int size = matrix.length;
        int[][] newMatrix = new int[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newMatrix[i][j] = getAverage(matrix, i, j, n);
            }
        }
        return newMatrix;
    }
    
    public static int getAverage(int[][] matrix, int row, int col, int n) {
        int size = matrix.length;
        int sum = 0;
        int count = 0;
        
        for (int i = row - n; i <= row + n; i++) {
            for (int j = col - n; j <= col + n; j++) {
                if (i >= 0 && i < size && j >= 0 && j < size) {
                    sum += matrix[i][j];
                }
                count++;
            }
        }
        return sum / count;
    }
    
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}