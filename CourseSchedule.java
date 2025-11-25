import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.HashMap;

class Solution {
  // Given numCourses and a list of prerequisite pairs where prerequisites[i] =
  // [a, b] means course a depends on course b, determine if it's possible to
  // finish all courses. Return true if possible, otherwise return false.
  //
  // Example:
  //
  // Input: numCourses = 2, prerequisites = [[1,0]]
  // Output: true
  // Explanation: Course 1 requires Course 0, so you can complete them in order.
  //
  // Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
  // Output: false
  // Explanation: There is a cycle (0 → 1 → 0), making it impossible to complete
  // all coursesI.
  public static HashMap<Integer, Integer> indegree(HashMap<Integer, List<Integer>> graph) {
    HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
    graph.entrySet().forEach(entry -> {
      result.put(entry.getKey(), 0);
    });
    graph.entrySet().forEach(entry -> {
      for (Integer value : entry.getValue()) {
        result.put(value, result.get(value) + 1);
      }
    });
    return result;
  }

  public static boolean isValidCourseSchedule(int n, List<List<Integer>> prerequisites) {
    HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
    for (int i = 0; i < n; i++) {
      graph.put(i, new ArrayList<>());
    }

    for (List<Integer> prerequisite : prerequisites) {
      Integer u = prerequisite.get(0);
      Integer v = prerequisite.get(1);
      graph.get(v).add(u);
    }

    ArrayDeque<Integer> q = new ArrayDeque<>();
    HashMap<Integer, Integer> inde = indegree(graph);
    inde.entrySet().forEach(entry -> {
      if (entry.getValue() == 0) {
        q.push(entry.getKey());
      }
    });

    while (!q.isEmpty()) {
      Integer front = q.poll();
      for (Integer neighbor : graph.get(front)) {
        inde.put(neighbor, inde.get(neighbor) - 1);
        if (inde.get(neighbor) == 0) {
          q.add(neighbor);
        }
      }
    }

    for (Integer value : inde.values()) {
      if (value != 0)
        return false;
    }
    return true;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseInt(scanner.nextLine());
    int prerequisitesLength = Integer.parseInt(scanner.nextLine());
    List<List<Integer>> prerequisites = new ArrayList<>();
    for (int i = 0; i < prerequisitesLength; i++) {
      prerequisites.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
    }
    scanner.close();
    boolean res = isValidCourseSchedule(n, prerequisites);
    System.out.println(res);
  }
}
