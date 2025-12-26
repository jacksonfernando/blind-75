import java.util.Iterator;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

  public static Node<Integer> lca(Node<Integer> root, Node<Integer> node1, Node<Integer> node2) {
    if (root == null)
      return null;
    if (root.equals(node1) || root.equals(node2))
      return root;
    Node<Integer> left = lca(root.left, node1, node2);
    Node<Integer> right = lca(root.right, node1, node2);
    if (left != null && right != null)
      return root;
    if (left != null)
      return left;
    if (right != null)
      return right;
    return null;
  }

  public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
    String val = iter.next();
    if (val.equals("x"))
      return null;
    Node<T> left = buildTree(iter, f);
    Node<T> right = buildTree(iter, f);
    return new Node<T>(f.apply(val), left, right);
  }

  public static <T> Node<T> findNode(Node<T> root, T target) {
    if (root == null)
      return null;
    if (root.val == target)
      return root;
    Node<T> leftSearch = findNode(root.left, target);
    if (leftSearch == null) {
      return leftSearch;
    }
    return findNode(root.right, target);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Node<Integer> root = buildTree(Arrays.stream(scanner.nextLine().split(" ")).iterator(), Integer::parseInt);
    Node<Integer> node1 = findNode(root, Integer.parseInt(scanner.nextLine()));
    Node<Integer> node2 = findNode(root, Integer.parseInt(scanner.nextLine()));
    scanner.close();
    Node<Integer> ans = lca(root, node1, node2);
    System.out.println(ans == null ? "null" : ans.val);
  }
}
