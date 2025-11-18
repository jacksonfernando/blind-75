import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // Find Minimum in Rotated Sorted Array
  // Given a sorted array that has been rotated at some unknown pivot,
  // find the minimum element. You must write an algorithm that runs in O(log N)
  // time.
  // Example:
  //
  // Input: nums = [3,4,5,1,2]
  // Output: 1
  // Explanation: The minimum value in the rotated array is 1.
  //
  // Input: nums = [4,5,6,7,0,1,2]
  // Output: 0
  // Explanation: The minimum value in the rotated array is 0.
  public static int findMinRotated(List<Integer> arr) {
    int n = arr.size();
    int low = 0;
    int high = n - 1;
    int result = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr.get(mid) <= arr.get(high)) {
        high = mid - 1;
        result = mid;
      } else {
        low = mid + 1;
      }
    }
    return result;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> arr = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    scanner.close();
    int res = findMinRotated(arr);
    System.out.println(res);
  }
}
