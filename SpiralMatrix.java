import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // Given an m x n matrix, return all elements of the matrix in spiral order.
  // Spiral order starts from the top-left corner and moves in a clockwise spiral
  // pattern.
  // Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
  // Output: [1,2,3,6,9,8,7,4,5]
  // Explanation: The spiral order is shown in the image.
  //
  // Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
  // Output: [1,2,3,4,8,12,11,10,9,5,6,7]
  // Explanation: The spiral order is shown in the image.
  //
  // Input: matrix = [[1,2],[3,4]]
  // Output: [1,2,4,3]
  // Explanation: The spiral order is shown in the image.
  public static List<Integer> spiralMatrix(List<List<Integer>> matrix) {
    List<Integer> result = new ArrayList<>();
    if (matrix.isEmpty())
      return result;
    int top = 0;
    int bottom = matrix.size() - 1;
    int left = 0;
    int right = matrix.get(0).size() - 1;
    while (top <= bottom && left <= right) {
      for (int i = left; i <= right; i++) {
        result.add(matrix.get(top).get(i));
      }
      top++;

      for (int i = top; i <= bottom; i++) {
        result.add(matrix.get(i).get(right));
      }
      right--;

      if (top <= bottom) {
        for (int i = right; i >= left; i--) {
          result.add(matrix.get(bottom).get(i));
        }
        bottom--;
      }
      if (left <= right) {
        for (int i = bottom; i >= top; i--) {
          result.add(matrix.get(i).get(left));
        }
        left++;
      }
    }
    return result;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int matrixLength = Integer.parseInt(scanner.nextLine());
    List<List<Integer>> matrix = new ArrayList<>();
    for (int i = 0; i < matrixLength; i++) {
      matrix.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
    }
    scanner.close();
    List<Integer> res = spiralMatrix(matrix);
    System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
  }
}
