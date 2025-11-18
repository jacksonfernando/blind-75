import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // There is an integer array nums sorted in ascending order (with distinct
  // values).
  // Prior to being passed to your function,
  // nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length).
  // Given the array nums after the possible rotation and an integer target,
  // return the index of target if it is in nums, or -1 if it is not in nums. You
  // must write an algorithm with O(log n) runtime complexity.
  //
  // Example:
  //
  // Input: nums = [4,5,6,7,0,1,2], target = 0
  // Output: 4
  // Explanation: The array was rotated at index 4, and 0 is at index 4.
  //
  // Input: nums = [4,5,6,7,0,1,2], target = 3
  // Output: -1
  // Explanation: 3 is not in the array, so we return -1.
  //
  // Input: nums = [1], target = 0
  // Output: -1
  // Explanation: 0 is not in the array, so we return -1.
  public static int searchInRotatedSortedArray(List<Integer> nums, int target) {
    int n = nums.size();
    Integer low = 0;
    Integer high = n - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums.get(mid) == target)
        return mid;
      if (nums.get(low) <= nums.get(mid)) {
        if (nums.get(low) <= target && target < nums.get(mid)) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else {
        if (nums.get(mid) > target && target <= nums.get(high)) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
    }
    return -1;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    int target = Integer.parseInt(scanner.nextLine());
    scanner.close();
    int res = searchInRotatedSortedArray(nums, target);
    System.out.println(res);
  }
}
