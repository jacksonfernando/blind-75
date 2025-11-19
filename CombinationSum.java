import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {

  // Given an array of distinct integers candidates and a target integer target,
  // return all unique combinations of candidates where the chosen numbers sum to
  // target.
  // You may use the same number multiple times.
  // The answer can be returned in any order.
  //
  // Example:
  //
  // Input: candidates = [2,3,6,7], target = 7
  // Output: [[2,2,3],[7]]
  // Explanation:
  // - 2+2+3 = 7
  // - 7 = 7
  // - These are the only valid combinations.
  //
  // Input: candidates = [2,3,5], target = 8
  // Output: [[2,2,2,2],[2,3,3],[3,5]]
  // Explanation: Multiple combinations exist that sum to 8.
  public static void dfs(Integer idx, List<Integer> candidates, int target, List<List<Integer>> result,
      List<Integer> temp) {
    if (target == 0)
      result.add(new ArrayList<>(temp));
    if (target < 0)
      return;
    for (int i = idx; i < candidates.size(); i++) {
      int newTarget = target - candidates.get(i);
      temp.add(candidates.get(i));
      dfs(i, candidates, newTarget, result, temp);
      temp.removeLast();
    }
  }

  public static List<List<Integer>> combinationSum(List<Integer> candidates, int target) {
    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();
    dfs(0, candidates, target, result, temp);
    return result;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> candidates = splitWords(scanner.nextLine()).stream().map(Integer::parseInt)
        .collect(Collectors.toList());
    int target = Integer.parseInt(scanner.nextLine());
    scanner.close();
    List<List<Integer>> res = combinationSum(candidates, target);
    List<List<Integer>> resSorted = new ArrayList<>();
    for (List<Integer> row : res) {
      resSorted.add(row.stream().sorted().collect(Collectors.toList()));
    }
    resSorted.sort((l1, l2) -> {
      for (int i = 0; i < Math.min(l1.size(), l2.size()); i++) {
        if (l1.get(i) != l2.get(i)) {
          return l1.get(i) - l2.get(i);
        }
      }
      return l1.size() - l2.size();
    });
    for (List<Integer> row : resSorted) {
      System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
  }
}
