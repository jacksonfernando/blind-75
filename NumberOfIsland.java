import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // Given an m x n grid consisting of 1s (land) and 0s (water), return the number
  // of islands. An island is formed by connecting adjacent 1s vertically or
  // horizontally.
  // Input:
  // grid = [
  // ["1","1","0","0","0"],
  // ["1","1","0","0","0"],
  // ["0","0","1","0","0"],
  // ["0","0","0","1","1"]
  // ]
  // Output: 3
  // Explanation:
  // - The first island consists of the top-left 1s.
  // - The second island consists of the middle 1.
  // - The third island consists of the bottom-right 1s.
  //
  // Input:
  // grid = [
  // ["1","1","1","1","0"],
  // ["1","1","0","1","0"],
  // ["1","1","0","0","0"],
  // ["0","0","0","0","0"]
  // ]
  // Output: 1
  // Explanation: There is only one connected island.
  public static void dfs(List<List<Integer>> grid, int r, int c, int m, int n) {
    if (r < 0 || c < 0 || r >= m || c >= n || grid.get(r).get(c) == 0) {
      return;
    }
    grid.get(r).set(c, 0);
    List<Integer> deltaRow = Arrays.asList(0, -1, 0, 1);
    List<Integer> deltaCol = Arrays.asList(-1, 0, 1, 0);
    for (int i = 0; i < 4; i++) {
      int newRow = r + deltaRow.get(i);
      int newCol = c + deltaCol.get(i);
      if (newRow >= 0 && newCol >= 0 && newRow < m && newCol < n) {
        dfs(grid, newRow, newCol, m, n);
      }
    }
  }

  public static int countNumberOfIslands(List<List<Integer>> grid) {
    int result = 0;
    int m = grid.size();
    int n = grid.get(0).size();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid.get(i).get(j) == 0) {
          continue;
        }
        dfs(grid, i, j, m, n);
        result++;
      }
    }
    return result;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int gridLength = Integer.parseInt(scanner.nextLine());
    List<List<Integer>> grid = new ArrayList<>();
    for (int i = 0; i < gridLength; i++) {
      grid.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
    }
    scanner.close();
    int res = countNumberOfIslands(grid);
    System.out.println(res);
  }
}
