import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Solution {
  // You are given a string s and an integer k. You can choose any character of
  // the string and change it to any other uppercase English character. You can
  // perform this operation at most k times. Return the length of the longest
  // substring containing the same letter you can get after performing the above
  // operations.
  //
  // Example:
  //
  // Input: s = "ABAB", k = 2
  // Output: 4
  // Explanation: Replace the two 'A's with two 'B's or vice versa.
  //
  // Input: s = "AABABBA", k = 1
  // Output: 4
  // Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
  // The substring "BBBB" has the longest repeating letters, which is 4.
  //
  // Input: s = "AAAA", k = 2
  // Output: 4
  // Explanation: The string is already all 'A's, so no replacements are needed./
  public static int longestRepeatingCharacterReplacement(String s, int k) {
    List<Integer> characterCount = new ArrayList<>(26);
    for (int i = 0; i < 26; i++) {
      characterCount.add(0);
    }
    int left = 0;
    int maxFreq = 0;
    int maxLen = 0;
    for (int right = 0; right < s.length(); right++) {
      Character rightCh = s.charAt(right);
      int rightIdx = rightCh - 'A';
      characterCount.set(rightIdx, characterCount.get(rightIdx) + 1);

      maxFreq = Math.max(maxFreq, characterCount.get(rightIdx));
      if ((right - left + 1) - maxFreq > k) {
        Character leftCh = s.charAt(left);
        int leftIdx = leftCh - 'A';
        characterCount.set(leftIdx, characterCount.get(leftIdx) - 1);
        left++;
      }
      maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String s = scanner.nextLine();
    int k = Integer.parseInt(scanner.nextLine());
    scanner.close();
    int res = longestRepeatingCharacterReplacement(s, k);
    System.out.println(res);
  }
}
