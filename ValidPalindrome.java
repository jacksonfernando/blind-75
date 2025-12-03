import java.util.Scanner;

class Solution {
  // Given a string s, determine if it is a palindrome, considering only
  // alphanumeric characters and ignoring cases.

  // Example
  // Input: s = "A man, a plan, a canal: Panama"
  // Output: true
  // Explanation: After removing non-alphanumeric characters, it becomes
  // "amanaplanacanalpanama", which is a palindrome.
  //
  // Input: s = "race a car"
  // Output: false
  // Explanation: After removing non-alphanumeric characters, it becomes
  // "raceacar", which is not a palindrome.
  public static boolean isPalindrome(String s) {
    // WRITE YOUR BRILLIANT CODE HERE
    int low = 0;
    int high = s.length() - 1;
    while (low < high) {
      while (low < high && !Character.isLetterOrDigit(s.charAt(low))) {
        low++;
      }
      while (low < high && !Character.isLetterOrDigit(s.charAt(high))) {
        high--;
      }
      if (Character.toLowerCase(s.charAt(low)) != Character.toLowerCase(s.charAt(high))) {
        return false;
      }
      low++;
      high--;
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String s = scanner.nextLine();
    scanner.close();
    boolean res = isPalindrome(s);
    System.out.println(res);
  }
}
