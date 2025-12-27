import java.util.Scanner;

class Solution {
  public static int sumOfTwoIntegers(int a, int b) {
    while (b != 0) {
      int carry = (a & b) << 1;
      a = a ^ b;
      b = carry;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int a = Integer.parseInt(scanner.nextLine());
    int b = Integer.parseInt(scanner.nextLine());
    scanner.close();
    int res = sumOfTwoIntegers(a, b);
    System.out.println(res);
  }
}
