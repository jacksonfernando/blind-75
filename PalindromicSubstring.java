import java.util.Scanner;

class Solution {
  // Palindromic Substrings
  // Given a string s, return
  // the number of palindromic substrings in it.
  // A substring is a contigous sequence of characters
  // within the string. A palindrome is a string that reads
  // the same backward as forward.
  public static int palindromicSubstring(String s) {
    int n = s.length();
    if (n == 0)
      return 0;
    int result = 0;
    boolean[][] dp = new boolean[n][n];
    for (int i = 0; i < n; i++) {
      dp[i][i] = true;
      result++;
    }
    for (int i = 0; i < n - 1; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        dp[i][i + 1] = true;
        result++;
      }
    }

    for (int length = 3; length <= n; length++) {
      for (int i = 0; i < n - length + 1; i++) {
        int j = i + length - 1;
        if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
          dp[i][j] = true;
          result++;
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String s = scanner.nextLine();
    scanner.close();
    int res = palindromicSubstring(s);
    System.out.println(res);
  }
}
