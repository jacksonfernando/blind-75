import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // Given an integer array nums, return all unique triplets
  // [nums[i], nums[j], nums[k]] such that i != j != k
  // and nums[i] + nums[j] + nums[k] == 0. The solution set
  // must not contain duplicate triplets.
  // Example:
  // Input : nums = [-1, 0, 1, 2, -1, -4]
  // Output : [[-1, -1, 2], [-1, 0, 1]]
  // Explanation: The unique triplets that sum to zero are [-1, -1, 2] and [-1, 0,
  // 1].
  //
  //
  // 1. Iterate nums array, while using two pointers
  public static List<List<Integer>> tripletsWithSum0(List<Integer> nums) {
    Collections.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    int n = nums.size();
    for (int i = 0; i < n; i++) {
      if (nums.get(i) > 0)
        break;
      ;
      int low = i + 1;
      int high = n - 1;
      int target = -nums.get(i);

      while (low < high) {
        int sum = nums.get(low) + nums.get(high);

        if (sum > target) {
          high--;
        } else if (sum < target) {
          low++;
        } else {
          List<Integer> temp = Arrays.asList(nums.get(i), nums.get(low), nums.get(high));
          result.add(temp);
          while (low < high && nums.get(low) == temp.get(1))
            low++;
          while (low < high && nums.get(high) == nums.get(2))
            high--;
        }
      }

      while (i + 1 < n && nums.get(i) == nums.get(i + 1))
        i++;
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
    List<List<Integer>> res = tripletsWithSum0(nums);
    for (List<Integer> row : res) {
      System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
  }
}
