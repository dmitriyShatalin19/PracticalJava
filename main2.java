import java.util.*;

public class Main {
  public static void main(String[] args) {
    Object[] arr = "Hello".split("");
    shuffle(arr);
    System.out.println(Arrays.toString(arr));
  }
  
  static void shuffle(Object[] arr) {
    Random rnd = new Random();
    for (int i = 0; i < arr.length - 1; i++) {
      int j = rnd.nextInt(arr.length);
      Object temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }
  }
}
