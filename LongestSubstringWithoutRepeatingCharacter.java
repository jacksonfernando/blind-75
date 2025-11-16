
import java.util.HashMap;
import java.util.Scanner;

class Solution {
  // Given a string s, find the length of the longest substrig without repeating
  // characters.
  // Example:
  // Input: s = "abcabcbb"
  // Output: 3
  // Explanation: Then answer is "abc", with the length of 3.
  public static int longestSubstringWithoutRepeatingCharacters(String s) {
    int result = 0;
    int left = 0;
    HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
    for (int right = 0; right < s.length(); right++) {
      Character currentChar = s.charAt(right);
      hashMap.put(currentChar, hashMap.getOrDefault(currentChar, 0) + 1);
      while (hashMap.get(currentChar) > 1) {
        hashMap.put(s.charAt(left), hashMap.get(s.charAt(left)) - 1);
        left++;
      }
      result = Math.max(result, right - left + 1);
    }
    return result;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String s = scanner.nextLine();
    scanner.close();
    int res = longestSubstringWithoutRepeatingCharacters(s);
    System.out.println(res);
  }
}
