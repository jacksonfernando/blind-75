import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayDeque;

class Solution {
  // Given a reference of a node in a connected undirected graph,
  // return a deep copy (clone) of the graph. Each node in the graph
  // contain a val(int) and a list(List[Node]) of its neighbors.
  // Example:
  // Input: adjList = [[2, 4], [1, 3], [2, 4], [1, 3]]
  // Output: [[2, 4], [1, 3], [2, 4], [1, 3]]
  // Explanation: There are 4 nodes in the graph.
  // 1st node (val=1)'s neighbors are 2nd node (val=2) and 4th node (val=4).
  // 2nd node (val=2)'s neighbors are 1st node (val=1) and 3rd node (val=3).
  // 3rd node (val=3)'s neighbors are 2nd node (val=2) and 4th node (val=4).
  // 4th node (val=4)'s neighbors are 1st node (val=1) and 3rd node (val=3).
  public static List<List<Integer>> cloneGraph(List<List<Integer>> adjList) {
    int n = adjList.size();
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      result.add(new ArrayList<>());
    }
    ArrayDeque<Integer> q = new ArrayDeque<>();
    HashSet<Integer> visited = new HashSet<Integer>();
    q.add(0);
    visited.add(0);
    while (!q.isEmpty()) {
      Integer front = q.poll();
      result.get(front).addAll(adjList.get(front));
      for (Integer neighbor : adjList.get(front)) {
        int neighborIdx = neighbor - 1;
        if (neighborIdx < n && !visited.contains(neighborIdx)) {
          q.add(neighborIdx);
          visited.add(neighborIdx);
        }
      }
    }
    return result;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int adjListLength = Integer.parseInt(scanner.nextLine());
    List<List<Integer>> adjList = new ArrayList<>();
    for (int i = 0; i < adjListLength; i++) {
      adjList.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
    }
    scanner.close();

    List<List<Integer>> res = cloneGraph(adjList);
    for (List<Integer> row : res) {
      System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
  }
}
