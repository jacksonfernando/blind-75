import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Solution {
  // Given an m x n board of characters and a word, return true if the word exists
  // in the grid. The word can be constructed from letters in adjacent cells
  // (horizontally or vertically) but cannot reuse the same letter cell more than
  // once.
  //
  // Input:
  // board = [
  // ["A","B","C","E"],
  // ["S","F","C","S"],
  // ["A","D","E","E"]
  // ]
  // word = "ABCCED"
  // Output: true
  //
  // Input:
  // board = [
  // ["A","B","C","E"],
  // ["S","F","C","S"],
  // ["A","D","E","E"]
  // ]
  // word = "SEE"
  // Output: true
  //
  // Input:
  // board = [
  // ["A","B","C","E"],
  // ["S","F","C","S"],
  // ["A","D","E","E"]
  // ]
  // word = "ABCB"
  // Output: false
  public static boolean dfs(int r, int c, int m, int n, List<List<String>> board, String word, int index) {
    if (index == word.length())
      return true;
    if (r < 0 || c < 0 || r >= m || c >= n || board.get(r).get(c) == "#"
        || board.get(r).get(c).charAt(0) != word.charAt(index)) {
      return false;
    }
    String temp = board.get(r).get(c);
    board.get(r).set(c, "#");
    boolean found = dfs(r + 1, c, m, n, board, word, index + 1)
        || dfs(r, c + 1, m, n, board, word, index + 1)
        || dfs(r - 1, c, m, n, board, word, index + 1)
        || dfs(r, c - 1, m, n, board, word, index + 1);
    board.get(r).set(c, temp);
    return found;
  }

  public static boolean exist(List<List<String>> board, String word) {
    int m = board.size();
    int n = board.get(0).size();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (dfs(i, j, m, n, board, word, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int boardLength = Integer.parseInt(scanner.nextLine());
    List<List<String>> board = new ArrayList<>();
    for (int i = 0; i < boardLength; i++) {
      board.add(splitWords(scanner.nextLine()));
    }
    String word = scanner.nextLine();
    scanner.close();
    boolean res = exist(board, word);
    System.out.println(res);
  }
}
