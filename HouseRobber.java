import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // You are a professional robber planning to rob houses along a street. Each
  // house has a certain amount of money stashed. However, you cannot rob two
  // adjacent houses. Given an integer array nums representing the amount of money
  // at each house, return the maximum amount of money you can rob without
  // alerting the police.
  //
  // Input: nums = [4,2,3,1]
  //
  // Example:
  // Input: nums = [1,2,3,1]
  // Output: 4
  // Explanation: Rob house 1 (money = 1) and house 3 (money = 3), total = 1 + 3 =
  // 4.
  //
  // Input: nums = [2,7,9,3,1]
  // Output: 12
  // Explanation: Rob house 1 (money = 2), house 3 (money = 9), and house 5 (money
  // = 1), total = 2 + 9 + 1 = 12.
  public static int rob(List<Integer> nums) {
    int n = nums.size();
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = nums.get(0);
    if (n == 1)
      return dp[1];
    for (int i = 2; i <= n; i++) {
      dp[i] = Math.max(dp[i - 1], nums.get(i - 1) + dp[i - 2]);
    }
    return dp[n];
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    scanner.close();
    int res = rob(nums);
    System.out.println(res);
  }
}
