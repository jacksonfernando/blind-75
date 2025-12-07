
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

class Solution {
  public static int val = -1;

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

  // Given the root of a binary search tree (BST) and an integer k, return the kth
  // smallest value (1-indexed) of all the values of the nodes in the tree.
  //
  // Input: root = [3,1,4,null,2], k = 1
  // Output: 1
  // Explanation: The in-order traversal sequence is [1, 2, 3, 4]. The 1st
  // smallest element is 1.
  //
  // Input: root = [5,3,6,2,4,null,null,1], k = 3
  // Output: 3
  // Explanation: The in-order traversal sequence is [1, 2, 3, 4, 5, 6]. The 3rd
  // smallest element is 3.
  //
  public static int treeSize(Node<Integer> tree, int existingK, int k) {
    if (val != -1) {
      return -1;
    }
    if (tree == null) {
      return 0;
    }
    int leftSize = treeSize(tree.left, existingK, k);
    if (leftSize + existingK == k - 1) {
      val = tree.val;
      return -1;
    }
    int rightSize = treeSize(tree.right, existingK + leftSize + 1, k);
    if (val != -1) {
      return -1;
    }
    return leftSize + rightSize + 1;
  }

  public static int kthSmallest(Node<Integer> bst, int k) {
    val = -1;
    treeSize(bst, 0, k);
    return val;
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
    Node<Integer> bst = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
    int k = Integer.parseInt(scanner.nextLine());
    scanner.close();
    int res = kthSmallest(bst, k);
    System.out.println(res);
  }
}
