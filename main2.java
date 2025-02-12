import java.util.Random;
import java.util.Arrays;

public class ArrayShuffler {
    public static void shuffleArray(int[] array) {
        Random random = new Random();
        
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        
        shuffleArray(arr);
        
        System.out.println("Перемешанный массив: " + Arrays.toString(arr));
    }
}
