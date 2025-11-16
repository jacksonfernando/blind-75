import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // Give n nodes labeled fron 0 to n-1 and a list of undirected edges (each edge
  // is a pair of nodes),
  // write a funcion to check whether these edges make up a valid tree. A valid
  // tree.
  // must have exactly n-1 edges and be fully connected.
  // Example
  //
  // Input : n = 5, edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
  // Output : true
  // Explanation : The graph has 5 nodes and 4 edges, is fully connected and has
  // no cycles
  //
  // Input: n = 5, edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
  // Output: false
  // Explanation: The graph has a cycle between nodes 1, 2, and 3, so it's not a
  // valid tree.
  public static boolean graphValidTree(int n, List<List<Integer>> edges) {
    int edgesSize = edges.size();
    if (n - 1 != edgesSize)
      return false;
    int[] parent = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }
    for (List<Integer> edge : edges) {
      int x = edge.get(0);
      int y = edge.get(1);
      int parent_x = getParent(parent, x);
      int parent_y = getParent(parent, y);
      if (parent_x == parent_y)
        return false;
      parent[y] = parent_x;
    }
    return true;
  }

  static int getParent(int[] parent, int x) {
    if (parent[x] != x) {
      return getParent(parent, parent[x]);
    }
    return parent[x];
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseInt(scanner.nextLine());
    int edgesLength = Integer.parseInt(scanner.nextLine());
    List<List<Integer>> edges = new ArrayList<>();
    for (int i = 0; i < edgesLength; i++) {
      edges.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
    }
    scanner.close();
    boolean res = graphValidTree(n, edges);
    System.out.println(res);
  }
}
