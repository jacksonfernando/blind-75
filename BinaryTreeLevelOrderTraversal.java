import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

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

  // Given the root of a binary tree, return the level order traversal of its
  // nodes' values (i.e., from left to right, level by level).
  //
  // Input: root = [3,9,20,null,null,15,7]
  // Output: [[3],[9,20],[15,7]]
  // Explanation:
  // - Level 1: [3]
  // - Level 2: [9, 20]
  // - Level 3: [15, 7]
  //
  // Input: root = [1]
  // Output: [[1]]
  //
  // Input: root = []
  // Output: []
  public static List<List<Integer>> levelOrderTraversal(Node<Integer> root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null)
      return result;
    ArrayDeque<Node<Integer>> aq = new ArrayDeque<Node<Integer>>();
    aq.add(root);
    while (!aq.isEmpty()) {
      List<Integer> temp = new ArrayList<>();
      int levelSize = aq.size();
      for (int i = 0; i < levelSize; i++) {
        Node<Integer> front = aq.poll();
        temp.add(front.val);
        if (front.left != null)
          aq.add(front.left);
        if (front.right != null)
          aq.add(front.right);
      }
      result.add(temp);
    }
    return result;
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
    List<List<Integer>> res = levelOrderTraversal(root);
    for (List<Integer> row : res) {
      System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
  }
}
