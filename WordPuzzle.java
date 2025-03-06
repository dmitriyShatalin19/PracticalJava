import java.util.*;

public class WordPuzzle {
    private static List<String> words = Arrays.asList("СЛОВО", "ДУМАЙ", "МОЗГ", "ПАМЯТЬ", "РАЗУМ", "какоетослово", "огромноеслово", "ямал", "анды");
    private static char[][] grid;
    private static int size;
    private static final Random random = new Random();

    public static void main(String[] args) {
        while (true) {
            size = (int) Math.ceil(Math.sqrt(words.stream().mapToInt(String::length).sum() * 1.5));
            grid = new char[size][size];
            for (char[] row : grid) Arrays.fill(row, ' ');

            if (attemptToPlaceWords()) break;
        }
        fillEmptyCells();
        printGrid();
    }

    private static boolean attemptToPlaceWords() {
        for (String word : words) {
            if (!placeWord(word)) return false;
        }
        return true;
    }

    private static boolean placeWord(String word) {
        int attempts = 100;
        while (attempts-- > 0) {
            int row = random.nextInt(size), col = random.nextInt(size), dir = random.nextInt(3);
            int dRow = (dir == 1) ? 1 : (dir == 2 ? 1 : 0);
            int dCol = (dir == 0) ? 1 : (dir == 2 ? 1 : 0);

            if (row + dRow * (word.length() - 1) >= size || col + dCol * (word.length() - 1) >= size) continue;
            if (IntStream.range(0, word.length()).anyMatch(i -> grid[row + i * dRow][col + i * dCol] != ' ')) continue;

            IntStream.range(0, word.length()).forEach(i -> grid[row + i * dRow][col + i * dCol] = word.charAt(i));
            return true;
        }
        return false;
    }

    private static void fillEmptyCells() {
        for (char[] row : grid)
            Arrays.setAll(row, i -> row[i] == ' ' ? (char) ('А' + random.nextInt(32)) : row[i]);
    }

    private static void printGrid() {
        Arrays.stream(grid).map(String::new).forEach(System.out::println);
    }
}

