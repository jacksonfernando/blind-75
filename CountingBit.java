import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;

class Solution {
  // Given an integer n, return an array ans of length n + 1 such that for each i
  // (0 ≤ i ≤ n), the number of 1's in the binary representation of i is stored in
  // ans[i].
  // Example:
  //
  // Input: n = 2
  // Output: [0,1,1]
  // Explanation:
  // 0 --> 0 (0 ones)
  // 1 --> 1 (1 one)
  // 2 --> 10 (1 one)
  //
  // Input: n = 5
  // Output: [0,1,1,2,1,2]
  // Explanation:
  // 0 --> 0 (0 ones)
  // 1 --> 1 (1 one)
  // 2 --> 10 (1 one)
  // 3 --> 11 (2 ones)
  // 4 --> 100 (1 one)
  // 5 --> 101 (2 ones)
  public static List<Integer> countingBits(int n) {
    List<Integer> result = new ArrayList<>(n + 1);
    result.add(0);
    for (int i = 1; i <= n; i++) {
      result.add(result.get(i >> 1) + (i & 1));
    }
    return result;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseInt(scanner.nextLine());
    scanner.close();
    List<Integer> res = countingBits(n);
    System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
  }
}
