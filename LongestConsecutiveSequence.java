import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;

class Solution {
  // Given an unsorted array of integers nums, return the length
  // of the longest consecutive elements sequence.
  // You must write an algorithm that runs in O(n) time.
  //
  // Example:
  // Input: nums = [100, 4, 200, 1, 3, 2]
  // Output: 4
  // Explanation: The longest consecutive elements sequence [1, 2, 3, 4].
  // Therefore the length is 4
  //
  // Input: nums = [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
  // Output: 9
  // Explanation: The longest consecutive elements sequence is [0, 1, 2, 3, 4, 5,
  // 6, 7, 8]. Therefore its length is 9.

  public static int longestConsecutiveSequence(List<Integer> nums) {
    if (nums.isEmpty())
      return 0;
    HashSet<Integer> hashSet = new HashSet<>();
    hashSet.addAll(nums);
    int result = 1;

    for (int num : nums) {
      if (!hashSet.contains(num - 1)) {
        int count = 1;

        int nextNumber = num + 1;
        while (hashSet.contains(nextNumber)) {
          nextNumber++;
          count++;
        }

        result = Math.max(result, count);
      }
    }
    return result;
  }

  private static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    scanner.close();
    int res = longestConsecutiveSequence(nums);
    System.out.println(res);
  }
}
