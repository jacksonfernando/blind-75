import java.util.Scanner;
import java.util.ArrayDeque;

class Solution {
  // Given a string s containing just the characters (, ), {, }, [ and ],
  // determine if the input string is valid. An input string is valid if
  // Open brackets must be closed by the same type of brackets.
  // Open brackets must be closed in the correct order.
  // Every closing bracket has a corresponding open bracket of the same type.
  //
  // Example:
  // Input: s = "()[]{}"
  // Output: true
  //
  // Input: s = "(]"
  // Output: false

  public static boolean validParentheses(String s) {
    ArrayDeque<Character> q = new ArrayDeque<Character>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '}') {
        if (!q.isEmpty() && q.peekLast() == '{')
          q.pollLast();
        else
          return false;
      } else if (c == ']') {
        if (!q.isEmpty() && q.peekLast() == '[')
          q.pollLast();
        else
          return false;

      } else if (c == ')') {
        if (!q.isEmpty() && q.peekLast() == '(')
          q.pollLast();
        else
          return false;
      } else {
        q.offerLast(c);
      }
    }
    return q.isEmpty();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String s = scanner.nextLine();
    scanner.close();
    boolean res = validParentheses(s);
    System.out.println(res);
  }
}
