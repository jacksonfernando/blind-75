import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

class Solution {
  private static Set<String> res = new HashSet<String>();

  public static class TrieNode {
    public TrieNode[] children = new TrieNode[26];
    public String item = "";

    public TrieNode() {
    }
  }

  static class Trie {
    private TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    public void insert(String word) {
      TrieNode node = root;
      for (Character ch : word.toCharArray()) {
        if (node.children[ch - 'a'] == null) {
          node.children[ch - 'a'] = new TrieNode();
        }
        node = node.children[ch - 'a'];
      }
      node.item = word;
    }

    public boolean search(String word) {
      TrieNode node = root;
      for (Character ch : word.toCharArray()) {
        if (node.children[ch - 'a'] == null)
          return false;
        node = node.children[ch - 'a'];
      }
      return node.item.equals(word);
    }

    public boolean startsWith(String prefix) {
      TrieNode node = root;
      for (Character ch : prefix.toCharArray()) {
        if (node.children[ch - 'a'] == null)
          return false;
        node = node.children[ch - 'a'];
      }
      return true;
    }
  }

  // Given an m x n board of characters and a list of strings words, return all
  // words on the board. Each word must be constructed from letters of
  // sequentially adjacent cells, where adjacent cells are horizontally or
  // vertically neighboring. The same letter cell may not be used more than once
  // in a word.
  //
  // Example:
  //
  // Input: board =
  // [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
  // words = ["oath","pea","eat","rain"]
  // Output: ["eat","oath"]
  // Explanation: The words "eat" and "oath" can be found on the board.
  //
  // Input: board = [["a","b"],["c","d"]], words = ["abcb"]
  // Output: []
  // Explanation: The word "abcb" cannot be found on the board.
  public static List<String> wordSearchIi(List<String> matrix, List<String> words) {
    Trie trie = new Trie();
    for (String word : words) {
      trie.insert(word);
    }

    boolean[][] visited = new boolean[matrix.size()][matrix.get(0).length()];
    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.get(i).length(); j++) {
        dfs(matrix, visited, "", i, j, trie);
      }
    }

    return new ArrayList<String>(res);
  }

  public static void dfs(List<String> matrix, boolean[][] visited, String str, int x, int y, Trie trie) {
    if (x < 0 || x >= matrix.size() || y < 0 || y >= matrix.get(0).length())
      return;

    str += matrix.get(x).charAt(y);
    if (!trie.startsWith(str))
      return;

    if (trie.search(str)) {
      res.add(str);
    }

    visited[x][y] = true;
    dfs(matrix, visited, str, x - 1, y, trie);
    dfs(matrix, visited, str, x + 1, y, trie);
    dfs(matrix, visited, str, x, y - 1, trie);
    dfs(matrix, visited, str, x, y + 1, trie);
    visited[x][y] = false;
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
