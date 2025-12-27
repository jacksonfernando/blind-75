import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public static List<Integer> productOfArrayExceptSelf(List<Integer> nums) {
    int n = nums.size();
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      result.add(0);
    }

    int left = 1;
    for (int i = 0; i < n; i++) {
      result.set(i, left);
      left *= nums.get(i);
    }

    int right = 1;
    for (int i = n - 1; i >= 0; i--) {
      result.set(i, result.get(i) * right);
      right *= nums.get(i);
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
    List<Integer> res = productOfArrayExceptSelf(nums);
    System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
  }
}
