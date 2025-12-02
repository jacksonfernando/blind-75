import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // Given an array of meeting time intervals where intervals[i] = [starti, endi],
  // determine if a person could attend all meetings.
  //
  // Example:
  //
  // Input: intervals = [[0,30],[5,10],[15,20]]
  // Output: false
  // Explanation: The meetings overlap, so a person cannot attend all meetings.
  //
  // Input: intervals = [[7,10],[2,4]]
  // Output: true
  // Explanation: The meetings don't overlap, so a person can attend all meetings.
  //
  // Input: intervals = [[0,30],[5,10],[15,20],[5,15]]
  // Output: false
  // Explanation: The meetings overlap, so a person cannot attend all meetings.
  public static boolean meetingRooms(List<List<Integer>> intervals) {
    intervals.sort((a, b) -> a.get(0) - b.get(0));
    for (int i = 1; i < intervals.size(); i++) {
      if (intervals.get(i).get(0) < intervals.get(i - 1).get(1)) {
        return false;
      }
    }
    return true;
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
    boolean res = meetingRooms(intervals);
    System.out.println(res);
  }
}
