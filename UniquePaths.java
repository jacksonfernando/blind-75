import java.util.Scanner;

class Solution {
  // There is a robot on an m x n grid. The robot is initially located at the
  // top-left corner (i.e., grid[0][0]). The robot tries to move to the
  // bottom-right corner (i.e., grid[m-1][n-1]). The robot can only move either
  // down or right at any point in time. Given the two integers m and n, return
  // the number of possible unique paths that the robot can take to reach the
  // bottom-right corner.
  //
  // Example:
  // Input: m = 3, n = 7
  // Output: 28
  // Explanation: From the top-left corner, there are a total of 28 ways to reach
  // the bottom-right corner.
  //
  // Input: m = 3, n = 2
  // Output: 3
  // Explanation: From the top-left corner, there are a total of 3 ways to reach
  // the bottom-right corner:
  // 1. Right -> Down -> Down
  // 2. Down -> Down -> Right
  // 3. Down -> Right -> Down
  //
  // Input: m = 7, n = 3
  // Output: 28
  // Explanation: From the top-left corner, there are a total of 28 ways to reach
  // the bottom-right corner.

  public static int uniquePaths(int m, int n) {
    // WRITE YOUR BRILLIANT CODE HERE
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 1;
        } else {
          dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
      }
    }
    return dp[m - 1][n - 1];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int m = Integer.parseInt(scanner.nextLine());
    int n = Integer.parseInt(scanner.nextLine());
    scanner.close();
    int res = uniquePaths(m, n);
    System.out.println(res);
  }
}
