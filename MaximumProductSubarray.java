import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // Given an integer array nums, find a contiguous non-empty subarray
  // within the array that has the largest product, and return the product.
  // The test cases are generated so that the answer will fit in a 32-bit integer.
  // Example:
  //
  // Input: nums = [2,3,-2,4]
  // Output: 6
  // Explanation: The subarray [2,3] has the largest product 6.
  //
  // Input: nums = [-2,0,-1]
  // Output: 0
  // Explanation: The result cannot be 2, because [-2,-1] is not a subarray. The
  // largest product is 0.
  //
  // Input: nums = [-2,3,-4]
  // Output: 24
  // Explanation: The subarray [-2,3,-4] has the largest product 24.
  public static int maximumProductSubarray(List<Integer> nums) {
    int n = nums.size();
    Integer maxProduct = nums.get(0);
    Integer minProduct = nums.get(0);
    Integer result = nums.get(0);
    for (int i = 1; i < n; i++) {
      Integer currNumber = nums.get(i);
      if (currNumber < 0) {
        Integer temp = minProduct;
        minProduct = maxProduct;
        maxProduct = temp;
      }
      minProduct = Math.min(currNumber, minProduct * currNumber);
      maxProduct = Math.max(currNumber, maxProduct * currNumber);

      result = Math.max(result, maxProduct);
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
    int res = maximumProductSubarray(nums);
    System.out.println(res);
  }
}
