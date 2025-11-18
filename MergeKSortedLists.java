import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;

class Solution {

  // Given an array of k linked lists, where each linked list is sorted in
  // ascendig order,
  // merge all the linked lists into one sorted linked lists and return it.
  // Example:
  // Input: lists = [[1, 4, 5], [1, 3, 4], [2, 6]]
  // Output: [1, 1, 2, 3, 4, 4, 5, 6]
  public static class Element {
    Integer value;
    List<Integer> lists;
    Integer headIdx;

    Element(Integer value, List<Integer> lists, Integer headIdx) {
      this.value = value;
      this.lists = lists;
      this.headIdx = headIdx;
    }
  }

  public static List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
    List<Integer> result = new ArrayList<>();
    PriorityQueue<Element> pq = new PriorityQueue<>(lists.size(), Comparator.comparing(e -> e.value));
    for (List<Integer> list : lists) {
      pq.add(new Element(list.get(0), list, 0));
    }
    while (!pq.isEmpty()) {
      Element front = pq.poll();
      result.add(front.value);
      int currIdx = front.headIdx;
      if (currIdx + 1 < front.lists.size()) {
        front.headIdx = currIdx + 1;
        front.value = front.lists.get(front.headIdx);
        pq.add(front);
      }
    }
    return result;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int listsLength = Integer.parseInt(scanner.nextLine());
    List<List<Integer>> lists = new ArrayList<>();
    for (int i = 0; i < listsLength; i++) {
      lists.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
    }
    scanner.close();
    List<Integer> res = mergeKSortedLists(lists);
    System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
  }
}
