import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  public static class Node<T> {
    public T val;
    public Node<T> next;

    public Node(Ti val) {
      this(val, null);
    }

    public Node(T val, Node<T> next) {
      this.val = val;
      this.next = next;
    }
  }

  // Given a head of a linked list, determine if the linked list has cycle
  // in it. A cycle exist if there is some node in the list that can be reached
  // again by continuosly following hte next pointer.
  // Example:
  // Input: head = [1, 2, 3, 4, 1], pos = 1
  // Output: true;
  // Explanation: There is a cycle in the linked list, where the tail connects to
  // the 1st node (0-indexed).
  public static boolean hasCycle(Node<Integer> nodes) {
    Node<Integer> slow_ptr = nodes;
    Node<Integer> fast_ptr = nodes;

    while (fast_ptr.next != null && fast_ptr.next.next != null) {
      slow_ptr = slow_ptr.next;
      fast_ptr = fast_ptr.next.next;

      if (slow_ptr == fast_ptr)
        return true;
    }
    return false;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> rawInput = splitWords(scanner.nextLine()).stream().map(Integer::parseInt)
        .collect(Collectors.toList());
    ArrayList<Node<Integer>> nodesList = new ArrayList<>();
    for (int i = 0; i < rawInput.size(); i++) {
      nodesList.add(new Node<Integer>(i));
    }
    for (int i = 0; i < rawInput.size(); i++) {
      if (rawInput.get(i) != -1) {
        nodesList.get(i).next = nodesList.get(rawInput.get(i));
      }
    }
    Node<Integer> nodes = nodesList.get(0);
    scanner.close();
    boolean res = hasCycle(nodes);
    System.out.println(res);
  }
}
