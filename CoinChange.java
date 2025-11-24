import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // Given an integer array coins representing different denominations and an
  // integer amount, return the fewest number of coins needed to make up that
  // amount. If it is impossible, return -1.
  //
  // Example:
  //
  // Input: coins = [1,2,5], amount = 11
  // Output: 3
  // Explanation: The minimum number of coins to make 11 is 5 + 5 + 1.
  //
  // Input: coins = [2], amount = 3
  // Output: -1
  // Explanation: No combination of coins can make 3.
  //
  // Input: coins = [1], amount = 0
  // Output: 0
  // Explanation: No coins are needed for amount 0.
  public static int coinChange(List<Integer> coins, int amount) {
    long[] dp = new long[amount + 1];
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
      dp[i] = Integer.MAX_VALUE;
      for (Integer coin : coins) {
        dp[i] = Math.min(dp[i], i >= coin ? dp[i - coin] + 1 : Integer.MAX_VALUE);
      }
    }
    return dp[amount] == Integer.MAX_VALUE ? -1 : (int) dp[amount];
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> coins = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    int amount = Integer.parseInt(scanner.nextLine());
    scanner.close();
    int res = coinChange(coins, amount);
    System.out.println(res);
  }
}
