import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

class Solution {
  public static class Node<T> {
    public T val;
    public Node<T> left;
    public Node<T> right;

    public Node(T val) {
      this(val, null, null);
    }

    public Node(T val, Node<T> left, Node<T> right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  // Given the root of a binary tree, determine if it is a valid binary search
  // tree (BST). A valid BST must satisfy the following conditions
  // The left subtree of a node contains only nodes with values less than the
  // node's value.
  // The right subtree of a node contains only nodes with values greater than the
  // node's value.
  // Both left and right subtrees must also be binary search trees.
  //
  //
  // Input: root = [2,1,3]
  // Output: true
  // Explanation: The tree follows BST rules.
  //
  // Input: root = [5,1,4,null,null,3,6]
  // Output: false
  // Explanation: The node with value 4 has a left child with value 3, violating
  // the BST property.

  private static boolean dfs(Node<Integer> root, int min, int max) {
    if (root == null)
      return true;
    if (!(min < root.val && root.val < max))
      return false;
    return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
  }

  public static boolean validBst(Node<Integer> root) {
    return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  // this function builds a tree from input; you don't have to modify it
  // learn more about how trees are encoded in
  // https://algo.monster/problems/serializing_tree
  public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
    String val = iter.next();
    if (val.equals("x"))
      return null;
    Node<T> left = buildTree(iter, f);
    Node<T> right = buildTree(iter, f);
    return new Node<T>(f.apply(val), left, right);
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Node<Integer> root = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
    scanner.close();
    boolean res = validBst(root);
    System.out.println(res);
  }
}
