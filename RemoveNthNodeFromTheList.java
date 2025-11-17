import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

class Solution {
  public static class Node<T> {
    public T val;
    public Node<T> next;

    public Node(T val) {
      this(val, null);
    }

    public Node(T val, Node<T> next) {
      this.val = val;
      this.next = next;
    }
  }

  // Given the head of a linked list, remove the n-th node from the end of list
  // and return its head.
  // The list may be empty, and n is always valid (1 ≤ n ≤ length of list).
  //
  // Example:
  // Input: head = [1,2,3,4,5], n = 2
  // Output: [1,2,3,5]
  // Explanation: The second node from the end is 4, so we remove it.
  //
  // Input: head = [1], n = 1
  // Output: []
  // Explanation: The first (and only) node from the end is 1, so we remove it.
  //
  // Input: head = [1,2], n = 2
  // Output: [2]
  // Explanation: The second node from the end is 1, so we remove it.
  public static Node<Integer> removeNthNodeFromEndOfList(Node<Integer> head, int n) {
    Node<Integer> dummy = new Node<Integer>(0);
    dummy.next = head;
    Node<Integer> slow = dummy;
    Node<Integer> fast = dummy;
    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }

    while (fast.next != null) {
      slow = slow.next;
      fast = fast.next;
    }
    slow.next = slow.next.next;
    return dummy.next;
  }

  public static <T> Node<T> buildList(Iterator<String> iter, Function<String, T> f) {
    if (!iter.hasNext())
      return null;
    String val = iter.next();
    Node<T> next = buildList(iter, f);
    return new Node<T>(f.apply(val), next);
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static <T> void formatList(Node<T> node, List<String> out) {
    if (node == null)
      return;
    out.add(node.val.toString());
    formatList(node.next, out);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Node<Integer> head = buildList(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
    int n = Integer.parseInt(scanner.nextLine());
    scanner.close();
    Node<Integer> res = removeNthNodeFromEndOfList(head, n);
    ArrayList<String> resArr = new ArrayList<>();
    formatList(res, resArr);
    System.out.println(String.join(" ", resArr));
  }
}
