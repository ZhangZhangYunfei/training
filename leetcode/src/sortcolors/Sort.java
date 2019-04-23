package sortcolors;

public class Sort {

  private static int[] sort(int... values) {
    int front = 0;
    int back = values.length - 1;
    int current = 0;

    while (current <= back) {
      if (values[current] == 0) {
        int temp = values[front];
        values[front++] = values[current];
        values[current++] = temp;
      } else if (values[current] == 2) {
        int temp = values[back];
        values[back--] = values[current];
        values[current] = temp;
      } else {
        current++;
      }
    }
    return values;
  }

  private static void print(int... values) {
    for (int value : values) {
      System.out.print(value);
    }
  }

  public static void main(String[] args) {
    print(sort(0, 1, 2, 0, 1, 2));
  }
}
