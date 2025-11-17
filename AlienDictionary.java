import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
  // Given a list of words
  // sorted lexicographically in an unknown language,
  // determine he order of characters in the alphabet
  // of that language. If no valid ordering exists,
  // return an empty string.
  //
  // Example:
  // Input: words = ["wrt", "wrf", "er", "ett", "rftt"]
  // Output: "wertf"
  // Explanation: The order of letters is "w -> e -> r -> t -> f".
  //
  // Example:
  // Input: words = ["z", "x", "z"]
  // Ouput: ""
  // Explanation: There is a cycle (z -> x -> z) so no valid ordering exists.
  //
  static public HashMap<Character, Integer> find_indegree(HashMap<Character, List<Character>> graph) {
    HashMap<Character, Integer> hashMap = new HashMap<>();
    graph.entrySet().forEach(entry -> {
      hashMap.put(entry.getKey(), 0);
    });
    graph.entrySet().forEach(entry -> {
      for (Character ch : entry.getValue()) {
        hashMap.put(ch, hashMap.get(ch) + 1);
      }
    });
    return hashMap;
  }

  static List<Character> topoSort(HashMap<Character, List<Character>> graph) {
    List<Character> result = new ArrayList<>();
    PriorityQueue<Character> pq = new PriorityQueue<Character>();
    HashMap<Character, Integer> indegree = find_indegree(graph);
    indegree.entrySet().forEach(entry -> {
      if (entry.getValue() == 0) {
        pq.offer(entry.getKey());
      }
    });
    while (!pq.isEmpty()) {
      char ch = pq.poll();
      result.add(ch);
      for (Character neighbor : graph.get(ch)) {
        indegree.put(neighbor, indegree.get(neighbor) - 1);
        if (indegree.get(neighbor) == 0) {
          pq.add(neighbor);
        }
      }
    }
    for (Integer value : indegree.values()) {
      if (value != 0)
        return null;
    }
    return result;
  }

  static String alienOrder(List<String> words) {
    HashMap<Character, List<Character>> graph = new HashMap<Character, List<Character>>();
    for (String word : words) {
      for (int i = 0; i < word.length(); i++) {
        if (!graph.containsKey(word.charAt(i))) {
          graph.put(word.charAt(i), new ArrayList<>());
        }
      }
    }
    String prevWord = words.get(0);
    for (int i = 1; i < words.size(); i++) {
      String currentWord = words.get(i);
      for (int j = 0; j < prevWord.length() && j < currentWord.length(); j++) {
        if (prevWord.charAt(j) != currentWord.charAt(j)) {
          if (!graph.get(prevWord.charAt(j)).contains(currentWord.charAt(j))) {
            graph.get(prevWord.charAt(j)).add(currentWord.charAt(j));
          }
          break;
        }
      }
      prevWord = currentWord;
    }

    List<Character> sorted = topoSort(graph);
    if (sorted == null) {
      return "";
    }
    return sorted.stream().map(String::valueOf).collect(Collectors.joining());
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<String> words = splitWords(scanner.nextLine());
    scanner.close();
    String res = alienOrder(words);
    System.out.println(res);
  }
}
