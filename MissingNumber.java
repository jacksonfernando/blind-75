import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // GIven an array nums containing n district numbers in the range
  // [0, n], return the only number in the range thas is missing from the array.
  //
  // Example:
  // Input: nums = [3, 0, 1]
  // Output: 2
  // Explanation: n = 3 since there are 3 numbers, so all numbers are in the range
  // [0,3]. 2 is the missing number in the range since it does not appear in nums.
  public static int missingNumber(List<Integer> nums) {
    int n = nums.size();
    int summation = n * (n + 1) / 2;
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    return summation - sum;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    scanner.close();
    int res = missingNumber(nums);
    System.out.println(res);
  }
}
