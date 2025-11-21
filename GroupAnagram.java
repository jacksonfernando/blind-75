import java.util.*;

class Solution {
  // Given an array of strings strs, group the anagrams together. An anagram is a
  // word formed by rearranging the letters of another word.
  //
  // Example:
  // Input: strs = ["eat","tea","tan","ate","nat","bat"]
  // Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
  // Explanation: Words with the same characters (sorted) belong to the same
  // group.
  public static List<List<String>> groupAnagrams(List<String> strs) {
    HashMap<String, List<String>> hashMap = new HashMap<>();
    for (String str : strs) {
      char[] charArray = str.toCharArray();
      Arrays.sort(charArray);
      String sorted = new String(charArray);
      hashMap.computeIfAbsent(sorted, k -> new ArrayList<>()).add(str);
    }
    return new ArrayList<>(hashMap.values());
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<String> strs = splitWords(scanner.nextLine());
    scanner.close();

    List<List<String>> res = groupAnagrams(strs);
    List<List<String>> resSorted = new ArrayList<>();

    for (List<String> row : res) {
      List<String> copy = new ArrayList<>(row);
      copy.sort(Comparator.naturalOrder());
      resSorted.add(copy);
    }

    resSorted.sort((l1, l2) -> {
      for (int i = 0; i < Math.min(l1.size(), l2.size()); i++) {
        if (!l1.get(i).equals(l2.get(i))) {
          return l1.get(i).compareTo(l2.get(i));
        }
      }
      return l1.size() - l2.size();
    });

    for (List<String> row : resSorted) {
      System.out.println(String.join(" ", row));
    }
  }
}
