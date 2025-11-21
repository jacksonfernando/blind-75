import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;

class Solution {
  // Given an integer array nums, find the contiguous subarray (containing at
  // least one number) which has the largest sum and return its sum.
  //
  // Example:
  // Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
  // Output: 6
  // Explanation: The subarray [4,-1,2,1] has the largest sum = 6.
  //
  // Input: nums = [1]
  // Output: 1
  // Explanation: The subarray [1] has the largest sum = 1.
  //
  // Input: nums = [5,4,-1,7,8]
  // Output: 23
  // Explanation: The subarray [5,4,-1,7,8] has the largest sum = 23.
  public static int maximumSubarray(List<Integer> nums) {
    int n = nums.size();
    int[] dp = new int[n];
    dp[0] = nums.get(0);
    int result = dp[0];
    for (int i = 1; i < n; i++) {
      dp[i] = Math.max(nums.get(i) + dp[i - 1], nums.get(i));
      result = Math.max(result, dp[i]);
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
    int res = maximumSubarray(nums);
    System.out.println(res);
  }
}
