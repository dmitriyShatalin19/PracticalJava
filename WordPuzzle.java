import java.util.*;

public class WordPuzzle {
    private static List<String> words = Arrays.asList("СЛОВО", "ДУМАЙ", "МОЗГ", "ПАМЯТЬ", "РАЗУМ", "какоетослово", "огромноеслово", "ямал", "анды");
    private static char[][] grid;
    private static int size;
    private static final Random random = new Random();
    private static final int MAX_SIZE = 50;

    public static void main(String[] args) {
        for (size = (int) Math.ceil(Math.sqrt(words.stream().mapToInt(String::length).sum() * 1.5)); size <= MAX_SIZE; size++) {
            grid = new char[size][size];
            for (char[] row : grid) Arrays.fill(row, ' ');

            if (attemptToPlaceWords()) {
                fillEmptyCells();
                printGrid();
                return;
            }
        }
        System.out.println("Не уалось разместить все слова в допустимой матрице.");
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
            boolean canPlace = true;
            for (int i = 0; i < word.length(); i++) {
                if (grid[row + i * dRow][col + i * dCol] != ' ') {
                    canPlace = false;
                    break;
                }
            }
            if (!canPlace) continue;

            for (int i = 0; i < word.length(); i++) {
                grid[row + i * dRow][col + i * dCol] = word.charAt(i);
            }
            return true;
        }
        return false;
    }
    
    private static void fillEmptyCells() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == ' ') {
                    grid[i][j] = (char) ('А' + random.nextInt(32));
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

