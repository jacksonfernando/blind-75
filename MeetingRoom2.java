import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  //
  // Given an array of meeting time intervals intervals where intervals[i] =
  // [starti, endi], return the minimum number of conference rooms required.
  //
  // Example:
  //
  // Input: intervals = [[0,30],[5,10],[15,20]]
  // Output: 2
  // Explanation: We need two meeting rooms:
  // Room 1: [0,30]
  // Room 2: [5,10], [15,20]
  //
  // Input: intervals = [[7,10],[2,4]]
  // Output: 1
  // Explanation: We need one meeting room:
  // Room 1: [2,4], [7,10]
  //
  // Input: intervals = [[0,30],[5,10],[15,20],[5,15]]
  // Output: 3
  // Explanation: We need three meeting rooms:
  // Room 1: [0,30]
  // Room 2: [5,10], [15,20]
  // Room 3: [5,15]
  public static int minMeetingRooms(List<List<Integer>> intervals) {
    if (intervals.isEmpty())
      return 0;
    intervals.sort((a, b) -> a.get(0) - b.get(0));

    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    pq.offer(intervals.get(0).get(1));

    for (int i = 1; i < intervals.size(); i++) {
      if (intervals.get(i).get(0) >= pq.peek()) {
        pq.poll();
      }
      pq.offer(intervals.get(i).get(1));
    }
    return pq.size();
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
    int res = minMeetingRooms(intervals);
    System.out.println(res);
  }
}
