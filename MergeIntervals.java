import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  // Given an array of intervals where intervals[i] = [start, end], merge all
  // overlapping intervals and return an array of the non-overlapping intervals
  // that cover all the intervals in the input.
  //
  // Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
  // Output: [[1,6],[8,10],[15,18]]
  // Explanation: Intervals [1,3] and [2,6] overlap, so they are merged into
  // [1,6].
  //
  // Input: intervals = [[1,4],[4,5]]
  // Output: [[1,5]]
  // Explanation: Intervals [1,4] and [4,5] are considered overlapping.
  public static List<List<Integer>> mergeIntervals(List<List<Integer>> intervals) {
    int n = intervals.size();
    List<List<Integer>> result = new ArrayList<>();
    intervals.sort((a, b) -> Integer.compare(a.get(0), b.get(0)));
    result.add(intervals.get(0));
    for (int i = 1; i < n; i++) {
      List<Integer> lastInterval = result.getLast();
      List<Integer> currentInterval = intervals.get(i);
      if (currentInterval.get(0) <= lastInterval.get(1)) {
        List<Integer> newInterval = new ArrayList<>();
        newInterval.add(Math.min(lastInterval.get(0), currentInterval.get(0)));
        newInterval.add(Math.max(lastInterval.get(1), currentInterval.get(1)));
        result.set(result.size() - 1, newInterval);
      } else {
        result.add(currentInterval);
      }
    }
    return result;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));

  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int intervalsLength = Integer.parseInt(scanner.nextLine());
    List<List<Integer>> intervals = new ArrayList<>();
    for (int i = 0; i < intervalsLength; i++) {
      intervals.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
    }
    scanner.close();
    List<List<Integer>> res = mergeIntervals(intervals);
    for (List<Integer> row : res) {
      System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
  }
}
