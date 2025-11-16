import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Solution {
  // Given a string s and a dictionary of strings wordDict,
  // return true if s can be segmented into a space-sperated sequence of
  // one or more dictionary words. The same word in the dictionary
  // may be resused multiple times in the segmentation.
  // Example:
  // Input : s : "leetcode" , wordDict = ["leet", "code"]
  // Output: true
  // Explanation: Return true because "leetcode" can be segmented as "leet code".
  //
  // Input: s = "applepenapple", wordDict = ["apple", "pen"]
  // Output: true
  // Explanation: Return true because "applepenapple" can be segemented as "apple
  // pen apple".
  //
  // Note that you are allowed to reuse a dictionary word.
  public static boolean dfs(int start, String s, List<String> words, Boolean[] memo) {
    if (start == s.length())
      return true;
    if (memo[start] != null)
      return memo[start];
    boolean ans = false;
    for (String word : words) {
      if (s.substring(start).startsWith(word)) {
        ans = ans || dfs(start + word.length(), s, words, memo);
      }
    }
    memo[start] = ans;
    return ans;
  }

  public static boolean wordBreak(String s, List<String> words) {
    return dfs(0, s, words, new Boolean[s.length()]);
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String s = scanner.nextLine();
    List<String> words = splitWords(scanner.nextLine());
    scanner.close();
    boolean res = wordBreak(s, words);
    System.out.println(res);
  }
}
