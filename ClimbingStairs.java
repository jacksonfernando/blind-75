import java.util.Scanner;

class Solution {
  // You are climbing a staircase. It takes n steps to reach the top. Each time
  // you can climb 1 or 2 steps. In how many distinct ways can you reach the top?
  //
  //
  // Input: n = 2
  // Output: 2
  // Explanation: There are two ways to climb the stairs:
  // 1. 1 step + 1 step
  // 2. 2 steps
  //
  // Input: n = 3
  // Output: 3
  // Explanation: There are three ways to climb the stairs:
  // 1. 1 step + 1 step + 1 step
  // 2. 1 step + 2 steps
  // 3. 2 steps + 1 step
  public static long climbingStairs(int n) {
    long first = 1;
    long second = 2;
    if (n == 1)
      return first;
    for (int i = 3; i <= n; i++) {
      long temp = second;
      second = first + second;
      first = temp;
    }
    return second;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseInt(scanner.nextLine());
    scanner.close();
    long res = climbingStairs(n);
    System.out.println(res);
  }
}
