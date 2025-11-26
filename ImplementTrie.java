import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

class Solution {
  // Design and implement a Trie (Prefix Tree) with insert, search, and startsWith
  // operations.
  // Example:
  //
  // Input:
  // Trie trie = new Trie();
  // trie.insert("apple");
  // trie.search("apple"); // returns true
  // trie.search("app"); // returns false
  // trie.startsWith("app"); // returns true
  // trie.insert("app");
  // trie.search("app"); // returns true
  static class TrieNode {
    private Map<Character, TrieNode> children;
    private boolean isEndOfWorld;

    public TrieNode() {
      children = new HashMap<>();
      isEndOfWorld = false;
    }
  }

  static class Trie {
    private TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    public void insert(String word) {
      TrieNode head = root;
      for (Character ch : word.toCharArray()) {
        head.children.putIfAbsent(ch, new TrieNode());
        head = head.children.get(ch);
      }
      head.isEndOfWorld = true;
    }

    public boolean search(String word) {
      TrieNode head = root;
      for (Character ch : word.toCharArray()) {
        head = head.children.get(ch);
        if (head == null)
          return false;
      }
      return head.isEndOfWorld;
    }

    public boolean startsWith(String prefix) {
      TrieNode head = root;
      for (Character ch : prefix.toCharArray()) {
        head = head.children.get(ch);
        if (head == null)
          return false;
      }
      return true;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String[] values = scanner.nextLine().split(" ");
    scanner.close();

    Trie trie = new Trie();
    trie.insert(values[0]);
    System.out.println(String.valueOf(trie.search(values[1])).toLowerCase());
    System.out.println(String.valueOf(trie.search(values[2])).toLowerCase());
    System.out.println(String.valueOf(trie.startsWith(values[3])).toLowerCase());
  }
}
