import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {

  // Given an array nums where nums[i] represents the maximum jump length from
  // index i, determine if you can reach the last index.
  //
  // Example:
  // Input: nums = [2,3,1,1,4]
  // Output: true
  // Explanation: Start at index 0 with jump length 2, jump to index 1, then to
  // index 4.
  //
  // Input: nums = [3,2,1,0,4]
  // Output: false
  // Explanation: You reach index 3 but cannot move forward because nums[3] = 0.
  public static boolean jumpGame(List<Integer> nums) {
    if (nums.size() <= 0)
      return true;
    int max_reach = 0;
    for (int i = 0; i <= max_reach; i++) {
      int curr = nums.get(i);
      if (i + curr >= nums.size() - 1) {
        return true;
      }
      max_reach = Math.max(max_reach, i + curr);
    }
    return false;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    scanner.close();
    boolean res = jumpGame(nums);
    System.out.println(res);
  }
}
