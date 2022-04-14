package matrix;
import java.util.Deque;
import java.util.ArrayDeque;
/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

Example 1:

Input: grid = [[0,1],[1,0]]
Output: 2

Example 2:

Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/
public class Shortest_Path_in_Binary_Matrix_1091 {
    int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {-1, 1}, {-1, -1}, {1, 1}};
    int shortest = 0;

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
            return -1;

        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        Deque<int[]> queue = new ArrayDeque<>();
        q.offer(new int[] {0, 0});

        int ans = 0;
        while(q.size() > 0) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                if (curr[0] == n - 1 && curr[1] == m - 1)
                    return ans + 1;

                for (int j = 0; j < directions.length; j++) {
                    int newI = directions[j][0] + curr[0];
                    int newJ = directions[j][0] + curr[1];

                    if (newI >= 0 && newJ >= 0 && newI < n && newJ < m && !visited[newI][newJ] && grid[newI][newJ] == 0) {
                        queue.add(new int[] { newI, newJ });
                        visited[newI][newJ] = true;
                    }
                }
            }

            ans++;
        }

        return -1;
    }

    public static void main(String[] args) {
    	Shortest_Path_in_Binary_Matrix_1091 obj = new Shortest_Path_in_Binary_Matrix_1091();
    }
}