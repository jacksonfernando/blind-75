
import java.util.Scanner;

class Solution {
  // Given a string s, find the length of the longest substrig without repeating
  // characters.
  // Example:
  // Input: s = "abcabcbb"
  // Output: 3
  // Explanation: Then answer is "abc", with the length of 3.
  public static int longestSubstringWithoutRepeatingCharacters(String s) {
    return 0;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String s = scanner.nextLine();
    scanner.close();
    int res = longestSubstringWithoutRepeatingCharacters(s);
    System.out.println(res);
  }
}
