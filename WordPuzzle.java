import java.util.*;

public class WordPuzzle {
    private static List<String> words = Arrays.asList("СЛОВО", "ДУМАЙ", "МОЗГ", "РАЗУМ", "ПАМЯТЬ");
    private static char[][] grid;
    private static int size;

    public static void main(String[] args) {
        size = calculateSize(words);
        grid = new char[size][size];
        for (char[] row : grid) Arrays.fill(row, ' ');

        placeWords(words);
        printGrid();
    }

    private static int calculateSize(List<String> words) {
        int totalLength = words.stream().mapToInt(String::length).sum();
        return (int) Math.ceil(Math.sqrt(totalLength));
    }

    private static void placeWords(List<String> words) {
        int row = 0, col = 0;
        for (String word : words) {
            for (char c : word.toCharArray()) {
                grid[row][col] = c;
                col++;
                if (col >= size) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    private static void printGrid() {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}