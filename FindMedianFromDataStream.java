import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
  //
  // The median is the middle value in an ordered integer list. If the size of the
  // list is even, there is no middle value and the median is the mean of the two
  // middle values. For example, for arr = [2,3,4], the median is 3. For arr =
  // [2,3], the median is (2 + 3) / 2 = 2.5. Implement the MedianFinder class:
  // MedianFinder() initializes the MedianFinder object. void addNum(int num) adds
  // the integer num from the data stream to the data structure. double
  // findMedian() returns the median of all elements so far. Answers within 10^-5
  // of the actual answer will be accepted.
  // Example:
  //
  // Input
  // ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
  // [[], [1], [2], [], [3], []]
  // Output
  // [null, null, null, 1.5, null, 2.0]
  //
  // Explanation
  // MedianFinder medianFinder = new MedianFinder();
  // medianFinder.addNum(1); // arr = [1]
  // medianFinder.addNum(2); // arr = [1, 2]
  // medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
  // medianFinder.addNum(3); // arr[1, 2, 3]
  // medianFinder.findMedian(); // return 2.0
  //
  // Input
  // ["MedianFinder", "addNum", "findMedian", "addNum", "findMedian"]
  // [[], [2], [], [3], []]
  // Output
  // [null, null, 2.0, null, 2.5]
  //
  // Explanation
  // MedianFinder medianFinder = new MedianFinder();
  // medianFinder.addNum(2); // arr = [2]
  // medianFinder.findMedian(); // return 2.0
  // medianFinder.addNum(3); // arr = [2, 3]
  // medianFinder.findMedian(); // return 2.5
  public static double findMedianFromDataStream(List<Integer> nums) {
    PriorityQueue<Integer> first = new PriorityQueue<Integer>();
    PriorityQueue<Integer> second = new PriorityQueue<Integer>(Collections.reverseOrder());
    for (Integer num : nums) {
      first.offer(num);
      second.offer(first.poll());
      if (second.size() > first.size()) {
        first.offer(second.poll());
      }
    }
    return first.size() > second.size() ? first.peek() : (first.peek() + second.peek()) / 2.0;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    scanner.close();
    double res = findMedianFromDataStream(nums);
    System.out.printf("%.4f\n", res);
  }
}
