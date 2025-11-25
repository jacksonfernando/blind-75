import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // Given an m x n integer matrix matrix, if an element is 0, set its entire row
  // and column to 0's. You must do it in place.
  //
  // Example:
  //
  // Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
  // Output: [[1,0,1],[0,0,0],[1,0,1]]
  // Explanation: The matrix becomes:
  // [[1,0,1],
  // [0,0,0],
  // [1,0,1]]
  // because the element at (1,1) is 0, so we set its entire row and column to 0.
  //
  // Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
  // Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
  // Explanation: The matrix becomes: [[0,0,0,0],
  // [0,4,5,0],
  // [0,3,1,0]]
  // because the elements at (0,0) and (0,3) are 0, so we set their entire rows
  // and columns to 0.
  public static List<List<Integer>> setMatrixZeroes(List<List<Integer>> matrix) {
    int m = matrix.size();
    int n = matrix.get(0).size();
    boolean firstRowZero = false;
    boolean firstColZero = false;

    for (int i = 0; i < n; i++) {
      if (matrix.get(0).get(i) == 0) {
        firstRowZero = true;
        break;
      }
    }

    for (int i = 0; i < m; i++) {
      if (matrix.get(i).get(0) == 0) {
        firstColZero = true;
        break;
      }
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (matrix.get(i).get(j) == 0) {
          matrix.get(i).set(0, 0);
          matrix.get(0).set(j, 0);
        }
      }
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (matrix.get(i).get(0) == 0 || matrix.get(0).get(j) == 0) {
          matrix.get(i).set(j, 0);
        }
      }
    }

    if (firstRowZero) {
      for (int i = 0; i < n; i++) {
        matrix.get(0).set(i, 0);
      }
    }

    if (firstColZero) {
      for (int i = 0; i < m; i++) {
        matrix.get(i).set(0, 0);
      }
    }
    return matrix;
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
    List<List<Integer>> res = setMatrixZeroes(matrix);
    for (List<Integer> row : res) {
      System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
  }
}
