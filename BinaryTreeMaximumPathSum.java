import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.function.Function;

class Solution {
  public static int maxSum = Integer.MIN_VALUE;

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

  private static int dfs(Node<Integer> node) {
    if (node == null) {
      return 0;
    }

    int left = Math.max(dfs(node.left), 0);
    int right = Math.max(dfs(node.right), 0);

    maxSum = Math.max(maxSum, node.val + left + right);

    return node.val + Math.max(left, right);
  }

  public static int maxPathSum(Node<Integer> root) {
    dfs(root);
    return maxSum;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static <T> Node<T> buildTree(Iterator<String> iter, Function<String, T> f) {
    String val = iter.next();
    if (val.equals("x"))
      return null;
    Node<T> left = buildTree(iter, f);
    Node<T> right = buildTree(iter, f);
    return new Node<T>(f.apply(val), left, right);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Node<Integer> root = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
    scanner.close();
    int res = maxPathSum(root);
    System.out.println(res);
  }
}
