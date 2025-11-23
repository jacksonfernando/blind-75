import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {

  // You are given an array of non-overlapping intervals intervals where
  // intervals[i] = [starti, endi] represent the start and the end of the ith
  // interval and intervals is sorted in ascending order by starti. You are also
  // given an interval newInterval = [start, end] that represents the start and
  // end of another interval. Insert newInterval into intervals such that
  // intervals is still sorted in ascending order by starti and intervals still
  // does not have any overlapping intervals (merge overlapping intervals if
  // necessary).
  //
  // Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
  // Output: [[1,5],[6,9]]
  // Explanation: Because the new interval [2,5] overlaps with [1,3], we merge
  // them into [1,5].
  //
  // Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
  // Output: [[1,2],[3,10],[12,16]]
  // Explanation: Because the new interval [4,8] overlaps with [3,5] and [6,7], we
  // merge them into [3,10].
  //
  // Input: intervals = [], newInterval = [5,7]
  // Output: [[5,7]]
  // Explanation: There are no intervals, so we just insert the new interval.
  public static List<List<Integer>> insertInterval(List<List<Integer>> intervals, List<Integer> newInterval) {
    int n = intervals.size();
    intervals.sort((a, b) -> Integer.compare(a.get(0), b.get(0)));
    List<List<Integer>> result = new ArrayList<>();
    int i = 0;
    for (; i < n && intervals.get(i).get(1) < newInterval.get(0); i++) {
      result.add(intervals.get(i));
    }
    for (; i < n && intervals.get(i).get(0) <= newInterval.get(1); i++) {
      newInterval.set(0, Math.min(intervals.get(i).get(0), newInterval.get(0)));
      newInterval.set(1, Math.max(intervals.get(i).get(1), newInterval.get(1)));
    }
    result.add(newInterval);
    for (; i < n; i++) {
      result.add(intervals.get(i));
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
    List<Integer> newInterval = splitWords(scanner.nextLine()).stream().map(Integer::parseInt)
        .collect(Collectors.toList());
    scanner.close();
    List<List<Integer>> res = insertInterval(intervals, newInterval);
    for (List<Integer> row : res) {
      System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
  }
}
