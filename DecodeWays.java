import java.util.Arrays;
import java.util.Scanner;

class Solution {

  public static int dfs(int[] memo, String digits, int startIndex) {
    if (startIndex == digits.length())
      return 1;
    if (memo[startIndex] != -1)
      return memo[startIndex];

    int way = 0;
    if (digits.charAt(startIndex) == '0')
      return way;
    way += dfs(memo, digits, startIndex + 1);
    if (startIndex + 2 <= digits.length() && Integer.parseInt(digits.substring(startIndex, startIndex + 2)) <= 26) {
      way += dfs(memo, digits, startIndex + 2);
    }
    memo[startIndex] = way;
    return way;
  }

  // A message containing letters from A-Z can be encoded into numbers using the
  // following mapping: 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26". To decode an
  // encoded message, all the digits must be grouped and then mapped back into
  // letters using the reverse of the mapping above (there may be multiple ways).
  // For example, "11106" can be mapped into "AAJF" with the grouping (1 1 10 6),
  // but it can also be mapped into "KJF" with the grouping (11 10 6). Note that
  // the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F'
  // since "6" is different from "06". Given a string s containing only digits,
  // return the number of ways to decode it.
  //
  // Input: s = "12"
  /// Output: 2 Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
  ///
  /// Input: s = "226" Output: 3 Explanation: "226" could be decoded as "BZ" (2
  /// 26), "VF" (22 6), or "BBF" (2 2 6).
  ///
  /// Input: s = "06" Output: 0 Explanation: "06" cannot be mapped to "F" because
  /// of the leading zero ("6" is different from "06").
  public static int decodeWays(String digits) {
    int n = digits.length();
    int[] memo = new int[n + 1];
    Arrays.fill(memo, -1);
    return dfs(memo, digits, 0);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String digits = scanner.nextLine();
    scanner.close();
    int res = decodeWays(digits);
    System.out.println(res);
  }
}
