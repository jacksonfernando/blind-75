import java.util.ArrayList;
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

  // Serialization is the process of converting a data structure or object into a
  // sequence of bits so that it can be stored in a file or memory buffer, or
  // transmitted across a network connection link to be reconstructed later in the
  // same or another computer environment. Design an algorithm to serialize and
  // deserialize a binary tree. There is no restriction on how your
  // serialization/deserialization algorithm should work. You just need to ensure
  // that a binary tree can be serialized to a string and this string can be
  // deserialized to the original tree structure. Clarification: The input/output
  // format is the same as how LeetCode serializes a binary tree. You do not
  // necessarily need to follow this format, so please be creative and come up
  // with different approaches yourself.
  //
  // Example:
  //
  // Input: root = [1,2,3,null,null,4,5]
  // Output: [1,2,3,null,null,4,5]
  // Explanation: Your Codec object will be instantiated and called as such:
  // Codec ser = new Codec();
  // Codec deser = new Codec();
  // TreeNode ans = deser.deserialize(ser.serialize(root));
  //
  // Input: root = []
  // Output: []
  // Explanation: The empty tree is serialized as an empty string and deserialized
  // as an empty tree.
  //
  // Input: root = [1]
  // Output: [1]
  // Explanation: A tree with a single node is serialized as "1" and deserialized
  // back to a tree with a single node.
  public static String serialize(Node<Integer> node) {
    if (node == null) {
      return "x";
    }
    return node.val + " " + serialize(node.left) + " " + serialize(node.right);
  }

  public static Node<Integer> deserialize(String s) {
    return buildTree(Arrays.asList(s.split(" ")).stream().iterator());
  }

  private static Node<Integer> buildTree(Iterator<String> iterator) {
    String value = iterator.next();
    if (value.equals("x"))
      return null;
    Node<Integer> root = new Node<Integer>(Integer.parseInt(value));
    root.left = buildTree(iterator);
    root.right = buildTree(iterator);
    return root;
  }

  public static Node<Integer> serializeAndDeserializeBinaryTree(Node<Integer> root) {
    return deserialize(serialize(root));
  }

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

  public static <T> void formatTree(Node<T> node, List<String> out) {
    if (node == null) {
      out.add("x");
      return;
    }
    out.add(node.val.toString());
    formatTree(node.left, out);
    formatTree(node.right, out);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Node<Integer> root = buildTree(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
    scanner.close();
    Node<Integer> res = serializeAndDeserializeBinaryTree(root);
    ArrayList<String> resArr = new ArrayList<>();
    formatTree(res, resArr);
    System.out.println(String.join(" ", resArr));
  }
}
