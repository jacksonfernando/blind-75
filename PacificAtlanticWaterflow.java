import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Solution {
  // There is an m x n rectangular island that borders both the
  // Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left
  // and top edges, and the Atlantic Ocean touches the island's right and bottom
  // edges. The island is partitioned into a grid of square cells. You are given
  // an m x n integer matrix heights where heights[r][c] represents the height
  // above sea level of the cell at coordinate (r, c). The island receives a lot
  // of rain, and the rain water can flow to neighboring cells directly north,
  // south, east, and west if the neighboring cell's height is less than or equal
  // to the current cell's height. Water can flow from any cell adjacent to an
  // ocean into the ocean. Return a 2D list of grid coordinates result where
  // result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to
  // both the Pacific and Atlantic oceans.
  //
  // Example:
  //
  // Input: heights =
  // [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
  // Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
  // Explanation: The following cells can flow to the Pacific and Atlantic oceans,
  // as shown below:
  // [0,4]: [0,4] -> Pacific Ocean
  // [0,4] -> Atlantic Ocean
  // [1,3]: [1,3] -> [0,3] -> Pacific Ocean
  // [1,3] -> [1,4] -> [1,3] -> [2,3] -> [2,2] -> [3,2] -> [4,2] -> Atlantic Ocean
  // [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
  // [1,4] -> Atlantic Ocean
  // [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
  // [2,2] -> [2,3] -> [2,4] -> [1,4] -> Atlantic Ocean
  // [3,0]: [3,0] -> Pacific Ocean
  // [3,0] -> [4,0] -> Atlantic Ocean
  // [3,1]: [3,1] -> [3,0] -> Pacific Ocean
  // [3,1] -> [4,1] -> Atlantic Ocean
  // [4,0]: [4,0] -> Pacific Ocean
  // [4,0] -> Atlantic Ocean
  //
  // Input: heights = [[2,1],[1,2]]
  // Output: [[0,0],[0,1],[1,0],[1,1]]
  // Explanation: Every cell can flow to both the Pacific and Atlantic oceans.
  public static void dfs(int r, int c, int m, int n, List<List<Integer>> heights, List<List<Boolean>> ocean) {
    if (r < 0 || c < 0 || r >= m || c >= n || ocean.get(r).get(c)) {
      return;
    }
    ocean.get(r).set(c, true);
    List<Integer> deltaRow = Arrays.asList(0, -1, 0, 1);
    List<Integer> deltaCol = Arrays.asList(-1, 0, 1, 0);
    for (int i = 0; i < 4; i++) {
      Integer newRow = deltaRow.get(i) + r;
      Integer newCol = deltaCol.get(i) + c;
      if (newRow >= 0 && newCol >= 0 && newRow < m && newCol < n) {
        if (heights.get(r).get(c) <= heights.get(newRow).get(newCol)) {
          dfs(newRow, newCol, m, n, heights, ocean);
        }
      }
    }
  }

  public static List<List<Integer>> pacificAtlanticWaterFlow(List<List<Integer>> heights) {
    List<List<Integer>> result = new ArrayList<>();
    int m = heights.size();
    int n = heights.get(0).size();
    List<List<Boolean>> pacific = new ArrayList<>();
    List<List<Boolean>> atlantic = new ArrayList<>();

    for (int i = 0; i < m; i++) {
      List<Boolean> pacificRow = new ArrayList<>(n);
      List<Boolean> atlanticRow = new ArrayList<>(n);
      for (int j = 0; j < n; j++) {
        pacificRow.add(false);
        atlanticRow.add(false);
      }
      pacific.add(pacificRow);
      atlantic.add(atlanticRow);
    }

    for (int r = 0; r < m; r++) {
      dfs(r, 0, m, n, heights, pacific);
      dfs(r, n - 1, m, n, heights, atlantic);
    }
    for (int c = 0; c < n; c++) {
      dfs(0, c, m, n, heights, pacific);
      dfs(m - 1, c, m, n, heights, atlantic);
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (pacific.get(i).get(j) && atlantic.get(i).get(j)) {
          result.add(Arrays.asList(i, j));
        }
      }
    }

    return result;
  }

  public static List<String> splitWords(String s) {
    return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int heightsLength = Integer.parseInt(scanner.nextLine());
    List<List<Integer>> heights = new ArrayList<>();
    for (int i = 0; i < heightsLength; i++) {
      heights.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
    }
    scanner.close();
    List<List<Integer>> res = pacificAtlanticWaterFlow(heights);
    for (List<Integer> row : res) {
      System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
  }
}
