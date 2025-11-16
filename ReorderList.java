import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
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

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  // Given the head of a singly linked list, reorder it
  // to follow this patttern - the first node, then the
  // last node, then the second node, then the second-last node,
  // and so on. The ordering must be done in-place
  // without modifying node values.
  // Example:
  // Input: head = [1, 2, 3, 4, 5]
  // Output: [1, 5, 2, 4, 3]
  public static Node<Integer> reorderList(Node<Integer> head) {
    Node<Integer> slow = head;
    Node<Integer> fast = head;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    Node<Integer> prev = null;
    Node<Integer> current = slow.next;
    slow.next = null;
    while (current != null) {
      Node<Integer> next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }

    Node<Integer> first = head;
    Node<Integer> second = prev;
    while (second != null) {
      Node<Integer> first_next = first.next;
      Node<Integer> second_next = second.next;

      first.next = second;
      second.next = first_next;
      first = first_next;
      second = second_next;
    }

    return head;
  }

  public static <T> Node<T> buildList(Iterator<String> iter, Function<String, T> f) {
    if (!iter.hasNext())
      return null;
    String val = iter.next();
    Node<T> next = buildList(iter, f);
    return new Node(f.apply(val), next);
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
    scanner.close();
    Node<Integer> res = reorderList(head);
    ArrayList<String> resArr = new ArrayList<>();
    formatList(res, resArr);
    System.out.println(String.join(" ", resArr));
  }
}
