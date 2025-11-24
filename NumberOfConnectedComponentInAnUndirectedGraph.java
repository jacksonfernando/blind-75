import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  //
  // Given an undirected graph with n nodes and edges, return the number of
  // connected components in the graph. A connected component is a subgraph in
  // which any two vertices are connected to each other by at least one path, and
  // which is connected to no additional vertices in the supergraph.
  //
  // Example:
  //
  // Input: n = 5, edges = [[0,1], [1,2], [3,4]]
  // Output: 2
  // Explanation: The graph has 2 connected components:
  // Component 1: 0-1-2
  // Component 2: 3-4
  //
  // Input: n = 5, edges = [[0,1], [1,2], [2,3], [3,4]]
  // Output: 1
  // Explanation: The graph has 1 connected component:
  // 0-1-2-3-4
  //
  // Input: n = 5, edges = []
  // Output: 5
  // Explanation: Each node is its own connected component.
  //
  public static int numberOfConnectedComponentsInAnUndirectedGraph(int n, List<List<Integer>> edges) {
    int result = 0;
    List<Boolean> visited = new ArrayList<>();
    List<List<Integer>> adjecencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adjecencyList.add(new ArrayList<>());
      visited.add(false);
    }
    for (List<Integer> edge : edges) {
      int u = edge.get(0);
      int v = edge.get(1);
      adjecencyList.get(u).add(v);
      adjecencyList.get(v).add(u);
    }

    for (int i = 0; i < n; i++) {
      if (visited.get(i) == false) {
        dfs(adjecencyList, visited, i);
        result++;
      }
    }
    return result;
  }

  public static void dfs(List<List<Integer>> graph, List<Boolean> visited, Integer node) {
    visited.set(node, true);
    for (Integer neighbor : graph.get(node)) {
      if (visited.get(neighbor) == false) {
        dfs(graph, visited, neighbor);
      }
    }
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
    int res = numberOfConnectedComponentsInAnUndirectedGraph(n, edges);
    System.out.println(res);
  }
}
