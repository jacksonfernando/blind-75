import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Solution {
  public static int sz = 1;
  public static int[][] trie = new int[26][30000001];
  public static boolean[] reached = new boolean[30000001];
  public static boolean[][] visited = new boolean[101][101];

  public static void insert(String word, int triePos, int strPos) {
    if (strPos == word.length()) {
      return;
    }
    int chIdx = word.charAt(strPos) - 'a';
    if (trie[chIdx][triePos] == 0) {
      trie[chIdx][triePos] = sz++;
    }
    insert(word, trie[chIdx][triePos], strPos + 1);
  }

  public static boolean query(String word, int triePos, int strPos) {
    if (!reached[triePos])
      return false;
    if (strPos == word.length())
      return true;
    int chIdx = word.charAt(strPos) - 'a';
    return query(word, trie[chIdx][triePos], strPos + 1);
  }

  public static void dfs(int i, int j, int trieIdx, List<String> matrix) {
    int m = matrix.size(), n = matrix.get(0).length();

    reached[trieIdx] = true;

    visited[i][j] = true;
    if (i + 1 < m && !visited[i + 1][j] && trie[matrix.get(i + 1).charAt(j) - 'a'][trieIdx] != 0) {
      dfs(i + 1, j, trie[matrix.get(i + 1).charAt(j) - 'a'][trieIdx], matrix);
    }

    if (i - 1 >= 0 && !visited[i - 1][j] && trie[matrix.get(i - 1).charAt(j) - 'a'][trieIdx] != 0) {
      dfs(i - 1, j, trie[matrix.get(i - 1).charAt(j) - 'a'][trieIdx], matrix);
    }

    if (j + 1 < n && !visited[i][j + 1] && trie[matrix.get(i).charAt(j + 1) - 'a'][trieIdx] != 0) {
      dfs(i, j + 1, trie[matrix.get(i).charAt(j + 1) - 'a'][trieIdx], matrix);
    }

    if (j - 1 >= 0 && !visited[i][j - 1] && trie[matrix.get(i).charAt(j - 1) - 'a'][trieIdx] != 0) {
      dfs(i, j - 1, trie[matrix.get(i).charAt(j - 1) - 'a'][trieIdx], matrix);
    }

    visited[i][j] = false;
  }

  public static List<String> wordSearchIi(List<String> matrix, List<String> words) {
    for (String word : words) {
      insert(word, 0, 0);
    }

    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.get(0).length(); j++) {
        int trieIdx = trie[matrix.get(i).charAt(j) - 'a'][0];
        if (trieIdx > 0) {
          dfs(i, j, trieIdx, matrix);
        }
      }
    }
    reached[0] = true;

    List<String> result = new ArrayList<>();
    for (int i = 0; i < words.size(); i++) {
      if (query(words.get(i), 0, 0))
        result.add(words.get(i));
    }
    return result;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<String> matrix = splitWords(scanner.nextLine());
    List<String> words = splitWords(scanner.nextLine());
    scanner.close();
    List<String> res = wordSearchIi(matrix, words);
    System.out.println(String.join(" ", res));
  }
}
