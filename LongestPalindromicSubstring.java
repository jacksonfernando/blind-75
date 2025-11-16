import java.util.Scanner;

// Given a string s, return the longest palindtromic substring in s.
// A palinrome is a string that reads the same 
// backaward as forward
//
// Example s = "babad"
// Output: "bab"
// Explanation:  "aba" is also a valid answer 
//
// Input: s = "cbbd"
// Output: "bb"
class Solution {

  public static String longestPalindromicSubstring(String s) {
    int n = s.length();

    if (n < 2)
      return s;

    boolean[][] dp = new boolean[n][n];

    for (int i = 0; i < n; i++) {
      dp[i][i] = true;
    }

    int start = 0;
    int maxLength = 1;

    for (int i = 0; i < n - 1; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        dp[i][i + 1] = true;
        start = i;
        maxLength = 2;
      }
    }

    for (int length = 3; length <= n; length++) {
      for (int i = 0; i < n - length + 1; i++) {
        int j = i + length - 1;
        if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
          dp[i][j] = true;
          if (length > maxLength) {
            start = i;
            maxLength = length;
          }
        }
      }
    }

    return s.substring(start, start + maxLength);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String s = scanner.nextLine();
    scanner.close();
    String res = longestPalindromicSubstring(s);
    System.out.println(res);
  }
}
