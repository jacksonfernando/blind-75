import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // Given an integer array height wehere height[i] represents
  // the height of a vertical line at index i, find two
  // lines that together with the x-axis form a container that hold the most water
  // Return the maximum amount of water a container can store.
  // Example:
  // Input: height = {1, 8, 6, 2, 5, 4, 8, 3, 7}
  // Output: 49
  // Explanation: The best container is formed between the second (height = 8) and
  // last (heigth = 7) vertical lines, storing 49 unit of water.
  public static int containerWithMostWater(List<Integer> arr) {
    int n = arr.size();
    int low = 0;
    int high = n - 1;
    int result = 0;
    while (low < high) {
      int width = high - low;
      result = Math.max(result, Math.min(arr.get(low), arr.get(high)) * width);
      if (arr.get(low) > arr.get(high)) {
        high--;
      } else {
        low++;
      }
    }
    return result;
  }

  public static List<String> splitsWord(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> arr = splitsWord(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    scanner.close();
    int res = containerWithMostWater(arr);
    System.out.println(res);
  }
}
