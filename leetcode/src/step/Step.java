package step;

public class Step {
  private static int calc(int n) {
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 2;
    }
    return calc(n - 1) + calc(n - 2);
  }

  private static int calc2(int n) {
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
      return 2;
    }
    int[] value = new int[n];
    value[0] = 1;
    value[1] = 2;
    for (int i = 2; i < n; i++) {
      value[i] = value[i - 1] + value[i - 2];
    }
    return value[n - 1];
  }

  public static void main(String[] args) {
    System.out.println(calc(10));
    System.out.println(calc2(10));
  }
}
