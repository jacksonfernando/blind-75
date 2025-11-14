import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.HashMap;

class Solution {
  // Given an array of integers nums and an integer target, return
  // the indices of the two numbers such tthat they add up to target.
  // You may assume that each input would have exactly one solution, and you
  // may not use the same element twice.
  public static List<Integer> twoSum(List<Integer> arr, int target) {
    HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
    for (int i = 0; i < arr.size(); i++) {
      int currentNumber = arr.get(i);
      if (hashMap.containsKey(currentNumber)) {
        return List.of(hashMap.get(currentNumber), i);
      } else {
        hashMap.put(target - currentNumber, i);
      }
    }
    return null;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> arr = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    int target = Integer.parseInt(scanner.nextLine());
    scanner.close();
    List<Integer> res = twoSum(arr, target);
    System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
  }
}
