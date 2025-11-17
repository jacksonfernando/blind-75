import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Solution {
  // Encode and Decode Strings
  // Design an algorithm to encode a list of string
  // to a string. The encoded string is then sent over the network
  // and is decodec back to the original list of strings.
  // please implement encode and decode functions.
  //
  // Example :
  // Input :["Hello", "World"]
  // Output: 5#Hello5#World
  // Explanation : The encoded string is "5#Hello5#World", where 5 is the lenght
  // of
  // "Hello " and "World"
  //
  // Input: [""]
  // Output: "0#"
  // Explanation: The encodes string is "0#" for an empty string
  //
  // Input: ["a", "b", "c"]
  // Output: "1#a1#b1#c"
  // Explanation: The encoded string is "1#a1#b1#c", wherei1 is then lenght of
  // each string.
  public static String encodeString(String[] strs) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strs.length; i++) {
      sb.append(strs[i].length()).append("#").append(strs[i]);
    }
    return sb.toString();
  }

  public static List<String> decodeString(String strs) {
    List<String> result = new ArrayList<>();
    int i = 0;
    while (i < strs.length()) {
      int j = i;
      while (strs.charAt(j) != '#') {
        j++;
      }
      int length = strs.charAt(j - 1);
      result.add(strs.substring(j + 1, j + 1 + length));
      i = j + 1 + length;
    }
    return result;
  }

  public static String encodeAndDecodeStrings(String strs) {
    String[] arrStrs = strs.split(" ");
    return encodeString(arrStrs);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String strs = scanner.nextLine();
    scanner.close();
    String res = encodeAndDecodeStrings(strs);
    System.out.println(res);
  }
}
