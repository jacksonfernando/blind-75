import java.util.Scanner;

class Solution {

  // Given two strings text1 and text2, return the length of their longest common
  // subsequence. A subsequence of a string is a new string generated from the
  // original string with some characters (can be none) deleted without changing
  // the relative order of the remaining characters.
  //
  //
  // Input: text1 = "abcde", text2 = "ace"
  // Output: 3
  // Explanation: The longest common subsequence is "ace" with length 3.
  //
  // Input: text1 = "abc", text2 = "abc"
  // Output: 3
  // Explanation: The longest common subsequence is "abc" with length 3.
  //
  // Input: text1 = "abc", text2 = "def"
  // Output: 0
  // Explanation: There is no common subsequence, so the result is 0.
  public static int dfs(String word1, String word2, int index1, int index2, Integer[][] memo) {
    if (index1 >= word1.length() || index2 >= word2.length()) {
      return 0;
    }
    if (memo[index1][index2] != null) {
      return memo[index1][index2];
    }
    int result = 0;
    if (word1.charAt(index1) == word2.charAt(index2)) {
      result = 1 + dfs(word1, word2, index1 + 1, index2 + 1, memo);
    } else {
      result = Math.max(dfs(word1, word2, index1 + 1, index2, memo), dfs(word1, word2, index1, index2 + 1, memo));
    }
    memo[index1][index2] = result;
    return result;
  }

  public static int longestCommonSubsequence(String word1, String word2) {
    return dfs(word1, word2, 0, 0, new Integer[word1.length()][word2.length()]);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String word1 = scanner.nextLine();
    String word2 = scanner.nextLine();
    scanner.close();
    int res = longestCommonSubsequence(word1, word2);
    System.out.println(res);
  }
}
