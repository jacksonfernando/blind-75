import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // You are given an n x n 2D matrix representing an image, rotate the image by
  // 90 degrees (clockwise). You have to rotate the image in-place, which means
  // you have to modify the input 2D matrix directly. DO NOT allocate another 2D
  // matrix and do the rotation.

  // Example:
  // Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
  // Output: [[7,4,1],[8,5,2],[9,6,3]]
  // Explanation: Rotate the matrix by 90 degrees clockwise:
  // [1,2,3] [7,4,1]
  // [4,5,6] -> [8,5,2]
  // [7,8,9] [9,6,3]
  //
  // Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
  // Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
  // Explanation: Rotate the matrix by 90 degrees clockwise.
  //
  // Input: matrix = [[1]]
  // Output: [[1]]
  // Explanation: A 1x1 matrix rotated by 90 degrees remains the same.
  public static List<List<Integer>> rotateImage(List<List<Integer>> matrix) {
    int n = matrix.size();
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        Integer temp = matrix.get(i).get(j);
        matrix.get(i).set(j, matrix.get(j).get(i));
        matrix.get(j).set(i, temp);
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n / 2; j++) {
        int temp = matrix.get(i).get(j);
        matrix.get(i).set(j, matrix.get(i).get(n - 1 - j));
        matrix.get(i).set(n - 1 - j, temp);
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
    List<List<Integer>> res = rotateImage(matrix);
    for (List<Integer> row : res) {
      System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
  }
}
