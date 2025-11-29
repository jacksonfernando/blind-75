import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.HashMap;

class Solution {
  // Given an integer array nums and an integer k, return the k most frequent
  // elements. You may return the answer in any order.
  //
  // Example:
  //
  // nput: nums = [1,1,1,2,2,3], k = 2
  // Output: [1,2]
  // Explanation: The two most frequent elements are 1 and 2.
  //
  // Input: nums = [1], k = 1
  // Output: [1]
  // Explanation: The most frequent element is 1.
  //
  // Input: nums = [1,1,1,2,2,2,3,3,3], k = 3
  // Output: [1,2,3]
  // Explanation: All elements appear the same number of times, so any three
  // elements are valid.
  public static List<Integer> topKFrequentElements(List<Integer> nums, int k) {
    List<Integer> result = new ArrayList<>();
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (Integer num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<Map.Entry<Integer, Integer>>((a, b) -> {
      int freq = Integer.compare(b.getValue(), a.getValue());
      if (freq != 0)
        return freq;
      return a.getKey() - b.getKey();
    });
    map.entrySet().forEach(entry -> {
      pq.offer(entry);
    });
    for (int i = 0; i < k; i++) {
      result.add(pq.poll().getKey());
    }
    return result;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
    int k = Integer.parseInt(scanner.nextLine());
    scanner.close();
    List<Integer> res = topKFrequentElements(nums, k);
    System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
  }
}
