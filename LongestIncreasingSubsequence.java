import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  private static int findUpperBound(int[] dp, int target) {
    int low = 0;
    int high = dp.length;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (dp[mid] > target) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  // Given an interger array nums, return the length of the longest strictly
  // increaseing subsequence. A subsequence is a sequence that can be derived
  // from an array by deleting some or no elements without
  // changing the order of the remaining elements.
  //
  // Example:
  //
  // Input: nums = [10,9,2,5,3,7,101,18]
  // Output: 4
  // Explanation: The longest increasing subsequence is [2,3,7,101], therefore the
  // length is 4.
  //
  // Input: nums = [0,1,0,3,2,3]
  // Output: 4
  // Explanation: The longest increasing subsequence is [0,1,2,3], therefore the
  // length is 4.
  //
  // Input: nums = [7,7,7,7,7,7,7]
  // Output: 1
  // Explanation: The longest increasing subsequence is [7], therefore the length
  // is 1.
  public static int longestSubLen(List<Integer> nums) {
    int n = nums.size();
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
      int upperBoundIdx = findUpperBound(dp, nums.get(i));
      if (dp[upperBoundIdx - 1] < nums.get(i)) {
        dp[upperBoundIdx] = nums.get(i);
      }
    }

    int result = 0;
    for (int i = 0; i <= n; i++) {
      if (dp[i] < Integer.MAX_VALUE) {
        result = i;
      }
    }
    return result;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    scanner.close();
    int res = longestSubLen(nums);
    System.out.println(res);
  }
}
