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

  // Given two sorted linked lists, merge them into one sorted linked list and
  // return the head of the merged list
  // Example:
  //
  // Input: list1 = [1, 2, 4] list2 = [1, 3, 4]
  // Output: [1, 1, 2, 3, 4, 4]
  //
  // Input: list1 = [], list2 = []
  // Output: []
  //
  // Input: list1 = [], list2 = [0]
  // Output: [0]
  public static Node<Integer> merge(Node<Integer> l1, Node<Integer> l2) {
    Node<Integer> dummy = new Node<Integer>(0);
    Node<Integer> current = dummy;
    while (l1 != null && l2 != null) {
      if (l1.val > l2.val) {
        current.next = l2;
        l2 = l2.next;
      } else {
        current.next = l1;
        l1 = l1.next;
      }
      current = current.next;
    }

    while (l1 != null) {
      current.next = l1;
      l1 = l1.next;
      current = current.next;
    }
    while (l2 != null) {
      current.next = l2;
      l2 = l2.next;
      current = current.next;
    }
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
    Node<Integer> l1 = buildList(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
    Node<Integer> l2 = buildList(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
    scanner.close();
    Node<Integer> res = merge(l1, l2);
    ArrayList<String> resArr = new ArrayList<>();
    formatList(res, resArr);
    System.out.println(String.join(" ", resArr));
  }
}
