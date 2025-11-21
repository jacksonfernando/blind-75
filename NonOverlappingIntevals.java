import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // Given an array of intervals intervals where intervals[i] = [starti, endi],
  // return the minimum number of intervals you need to remove to make the rest of
  // the intervals non-overlapping.
  //
  // Example:
  // Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
  // [1, 2], [2, 3], [1, 3], [3, 4]
  // Output: 1
  // Explanation: [1,3] can be removed and the rest of the intervals are
  // non-overlapping.
  //
  // Input: intervals = [[1,2],[1,2],[1,2]]
  // Output: 2
  // Explanation: You need to remove two [1,2] intervals to make the rest of the
  // intervals non-overlapping.
  //
  // Input: intervals = [[1,2],[2,3]]
  // Output: 0
  // Explanation: You don't need to remove any of the intervals since they're
  // already non-overlapping.
  public static int nonOverlappingIntervals(List<List<Integer>> intervals) {
    intervals.sort((a, b) -> Integer.compare(a.get(1), b.get(1)));
    int lastEnd = Integer.MIN_VALUE;
    int result = 0;
    for (List<Integer> interval : intervals) {
      if (interval.get(0) >= lastEnd) {
        lastEnd = interval.get(1);
      } else {
        result++;
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
    int res = nonOverlappingIntervals(intervals);
    System.out.println(res);
  }
}
