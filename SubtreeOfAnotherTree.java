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

  // Given the roots of two binary trees root and subRoot, return true if there is
  // a subtree of root with the same structure and node values as subRoot and
  // false otherwise. A subtree of a binary tree tree is a tree that consists of a
  // node in tree and all of this node's descendants. The tree tree could also be
  // considered as a subtree of itself.
  //
  // Example:
  //
  // Input: root = [3,4,5,1,2], subRoot = [4,1,2]
  // Output: true
  // Explanation: The subtree with root node 4 matches the structure and values of
  // subRoot.
  //
  // Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
  // Output: false
  // Explanation: There is no subtree with the same structure and values as
  // subRoot.
  //
  // Input: root = [1,1], subRoot = [1]
  // Output: true
  // Explanation: The subtree with root node 1 matches the structure and values of
  // subRoot.
  public static boolean isSameTree(Node<Integer> root, Node<Integer> subRoot) {
    if (root == null && subRoot == null)
      return true;
    if (root == null || subRoot == null)
      return false;
    return root.val == subRoot.val && isSameTree(root.left, subRoot.left)
        && isSameTree(root.right, subRoot.right);
  }

  public static boolean subtreeOfAnotherTree(Node<Integer> root, Node<Integer> subRoot) {
    if (root == null)
      return false;
    return isSameTree(root, subRoot) || subtreeOfAnotherTree(root.left, subRoot)
        || subtreeOfAnotherTree(root.right, subRoot);
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
    Node<Integer> subRoot = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
    scanner.close();
    boolean res = subtreeOfAnotherTree(root, subRoot);
    System.out.println(res);
  }
}
