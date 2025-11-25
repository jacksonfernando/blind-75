import java.util.Scanner;
import java.util.HashMap;

class Solution {
  // Given two strings s and t, return the minimum window in s that contains all
  // the characters in t. If no such window exists, return an empty string "".
  //
  // Example:
  //
  // Input: s = "ADOBECODEBANC", t = "ABC"
  // Output: "BANC"
  // Explanation: The minimum substring containing "A", "B", and "C" is "BANC".
  //
  // Input: s = "a", t = "a"
  // Output: "a"
  //
  // Input: s = "a", t = "aa"
  // Output: ""
  // Explanation: Since 'a' appears only once, but 't' requires two, there is no
  // valid window.
  public static String getMinimumWindow(String original, String check) {
    HashMap<Character, Integer> checkCount = new HashMap<Character, Integer>();
    HashMap<Character, Integer> windowCount = new HashMap<Character, Integer>();
    for (char ch : check.toCharArray()) {
      checkCount.merge(ch, 1, Integer::sum);
    }
    int satisfied = 0;
    int required = checkCount.size();
    int m = original.length();
    int window = -1;
    int length = m + 1;
    int left = 0;
    for (int right = 0; right < m; right++) {
      windowCount.put(original.charAt(right), windowCount.getOrDefault(original.charAt(right), 0) + 1);
      if (checkCount.get(original.charAt(right)) == windowCount.get(original.charAt(right))) {
        satisfied++;
      }
      while (satisfied == required) {
        if (right - left + 1 < length || right - left + 1 == length
            && original.substring(left, right + 1).compareTo(original.substring(window, window + length)) < 0) {
          window = left;
          length = right - left + 1;
        }
        windowCount.put(original.charAt(left), windowCount.get(original.charAt(left)) - 1);
        if (checkCount.containsKey(original.charAt(left))
            && windowCount.get(original.charAt(left)) < checkCount.get(original.charAt(left))) {
          satisfied--;
        }
        left++;
      }
    }
    return window >= 0 ? original.substring(window, window + length) : "";
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String original = scanner.nextLine();
    String check = scanner.nextLine();
    scanner.close();
    String res = getMinimumWindow(original, check);
    System.out.println(res);
  }
}
