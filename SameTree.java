import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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

  // Given the roots of two binary trees p and q, determine if they are the same.
  // Two binary trees are considered the same if they have the same structure and
  // the same node values.
  public static boolean sameTree(Node<Integer> p, Node<Integer> q) {
    if (p == null && q == null)
      return true;
    if (p == null || q == null) {
      return false;
    }
    return Objects.equals(p.val, q.val) && sameTree(p.left, q.left) && sameTree(p.right, q.right);
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
    Node<Integer> p = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
    Node<Integer> q = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
    scanner.close();
    boolean res = sameTree(p, q);
    System.out.println(res);
  }
}
