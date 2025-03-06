import java.util.*;

public class WordPuzzle {
    private static List<String> words = Arrays.asList("СЛОВО", "ДУМАЙ", "МОЗГ", "РАЗУМ", "ПАМЯТЬ");
    private static char[][] grid;
    private static int size;
    private static Random random = new Random();

    public static void main(String[] args) {
        size = calculateSize(words);
        grid = new char[size][size];
        for (char[] row : grid) Arrays.fill(row, ' ');

        placeWords(words);
        fillEmptyCells();
        printGrid();
    }

    private static int calculateSize(List<String> words) {
        int totalLength = words.stream().mapToInt(String::length).sum();
        return (int) Math.ceil(Math.sqrt(totalLength * 1.5));
    }
    private static void placeWords(List<String> words) {
        for (String word : words) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(size);
                int col = random.nextInt(size);
                int direction = random.nextInt(3); // 0 - горизонтально, 1 - вертикально, 2 - диагонально
                placed = tryPlaceWord(word, row, col, direction);
            }
        }
    }
    private static boolean tryPlaceWord(String word, int row, int col, int direction) {
        int len = word.length();
        int dRow = (direction == 1) ? 1 : (direction == 2 ? 1 : 0);
        int dCol = (direction == 0) ? 1 : (direction == 2 ? 1 : 0);

        if (row + dRow * (len - 1) >= size || col + dCol * (len - 1) >= size) return false;
        for (int i = 0; i < len; i++) {
            if (grid[row + i * dRow][col + i * dCol] != ' ') return false;
        }
        for (int i = 0; i < len; i++) {
            grid[row + i * dRow][col + i * dCol] = word.charAt(i);
        }
        return true;
    }
    private static void fillEmptyCells() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == ' ') {
                    grid[i][j] = (char) ('А' + random.nextInt(32)); // Заполнение случайными буквами
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
