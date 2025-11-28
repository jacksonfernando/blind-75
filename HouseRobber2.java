import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {

  // You are a professional robber planning to rob houses along a street. Each
  // house has a certain amount of money stashed. All houses at this place are
  // arranged in a circle. That means the first house is the neighbor of the last
  // one. Meanwhile, adjacent houses have a security system connected, and it will
  // automatically contact the police if two adjacent houses were broken into on
  // the same night. Given an integer array nums representing the amount of money
  // of each house, return the maximum amount of money you can rob tonight without
  // alerting the police.
  //
  // Example:
  //
  // Input: nums = [2,3,2]
  // Output: 3
  // Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money =
  // 2), because they are adjacent houses. The optimal solution is to rob house 2
  // (money = 3).
  //
  // Input: nums = [1,2,3,1]
  // Output: 4
  // Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total
  // amount you can rob = 1 + 3 = 4.
  //
  // Input: nums = [1,2,3]
  // Output: 3
  // Explanation: You cannot rob house 1 (money = 1) and then rob house 3 (money =
  // 3), because they are adjacent houses. The optimal solution is to rob house 2
  // (money = 2).
  //
  private static int rob(List<Integer> nums) {
    if (nums.size() == 0)
      return 0;
    int prev1 = 0;
    int prev2 = 0;
    for (Integer num : nums) {
      int temp = prev1;
      prev1 = Math.max(prev1, prev2 + num);
      prev2 = temp;
    }
    return prev1;
  }

  public static int houseRobberIi(List<Integer> nums) {
    if (nums.isEmpty())
      return 0;
    if (nums.size() == 1)
      return nums.get(0);
    return Math.max(rob(nums.subList(0, nums.size() - 1)), rob(nums.subList(1, nums.size())));
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    scanner.close();
    int res = houseRobberIi(nums);
    System.out.println(res);
  }
}
