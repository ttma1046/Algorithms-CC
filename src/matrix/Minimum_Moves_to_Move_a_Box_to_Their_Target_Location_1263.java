package matrix;
import java.util.Queue;
import java.util.LinkedList;
/*
A storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.

The game is represented by an m x n grid of characters grid where each element is a wall, floor, or box.

Your task is to move the box 'B' to the target position 'T' under the following rules:

The character 'S' represents the player. The player can move up, down, left, right in grid if it is a floor (empty cell).
The character '.' represents the floor which means a free cell to walk.
The character '#' represents the wall which means an obstacle (impossible to walk there).
There is only one box 'B' and one target cell 'T' in the grid.
The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
The player cannot walk through the box.
Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.

Example 1:

Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#",".","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 3
Explanation: We return only the number of times the box is pushed.

Example 2:

Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#","#","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: -1

Example 3:

Input: grid = [["#","#","#","#","#","#"],
               ["#","T",".",".","#","#"],
               ["#",".","#","B",".","#"],
               ["#",".",".",".",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 5
Explanation:  push the box down, left, left, up and up.
Example 4:

Input: grid = [["#","#","#","#","#","#","#"],
               ["#","S","#",".","B","T","#"],
               ["#","#","#","#","#","#","#"]]
Output: -1

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 20
grid contains only characters '.', '#', 'S', 'T', or 'B'.
There is only one character 'S', 'B', and 'T' in the grid.
*/

class Minimum_Moves_to_Move_a_Box_to_Their_Target_Location_1263 {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Minimum_Moves_to_Move_a_Box_to_Their_Target_Location_1263 obj = new Minimum_Moves_to_Move_a_Box_to_Their_Target_Location_1263();
    }


    public int minPushBox(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int step = 0;
        boolean[][][] visited = new boolean[m][n][4];

        Queue<Pair[]> queue = new LinkedList<>();
        Pair box = new Pair(-1, -1);
        Pair target = new Pair(-1, -1);
        Pair player = new Pair(-1, -1);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'B') box = new Pair(i, j);
                if (grid[i][j] == 'T') target = new Pair(i, j);
                if (grid[i][j] == 'S') player = new Pair(i, j);
            }
        }

        queue.offer(new Pair[] {box, player});

        while (!queue.isEmpty()) {
            for (int i = 0, l = queue.size(); i < l; i++) {
                Pair[] curr = queue.poll();
                Pair currBox = curr[0];

                if (currBox.row == target.row && currBox.col == target.col) return step; // check if box is on the target location
                
                for (int j = 0; j < 4; j++) {
                    if (visited[currBox.row][currBox.col][j]) continue;

                    int rPlayer = currBox.row + dx[j];
                    int cPlayer = currBox.col + dy[j];  // where pl stands, have room to push;
                    if (rPlayer < 0 || rPlayer >= m || cPlayer < 0 || cPlayer >= n || grid[rPlayer][cPlayer] == '#') continue;
                    

                    int rNextBox = currBox.row - dx[j];
                    int cNextBox = currBox.col - dy[j];  // box next spots;
                    if (rNextBox < 0 || rNextBox >= m || cNextBox < 0 || cNextBox >= n || grid[rNextBox][cNextBox] == '#') continue;

                    if (!reachable(grid, rPlayer, cPlayer, curr)) continue; // player reachable?
                        
                    visited[currBox.row][currBox.col][j] = true;

                    Pair boxNext = new Pair(rNextBox, cNextBox);
                    Pair playerNext = new Pair(currBox.row, currBox.col);

                    queue.offer(new Pair[] { boxNext, playerNext });
                }
            }

            step++;
        }

        return -1;
    }

    private boolean reachable(char[][] grid, int rPlayer, int cPlayer, Pair[] curr) {
        int m = grid.length;
        int n = grid[0].length;

        Pair box = curr[0];
        Pair player = curr[1];

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(player);
        
        boolean[][] visited = new boolean[m][n];
        visited[box.row][box.col] = true;

        while (!queue.isEmpty()) {
            Pair playerCur = queue.poll();
            if (playerCur.row == rPlayer && playerCur.col == cPlayer) return true;

            for (int k = 0; k < 4; k++) {
                int playerNextRow = playerCur.row - dx[k];
                int playerNextCol = playerCur.col - dy[k];  // player next spots;

                if (playerNextRow < 0 || playerNextRow >= m || playerNextCol < 0 || playerNextCol >= n || visited[playerNextRow][playerNextCol] || grid[playerNextRow][playerNextCol] == '#') continue;
                
                visited[playerNextRow][playerNextCol] = true;
                
                queue.offer(new Pair(playerNextRow, playerNextCol));
            }
        }

        return false;
    }
}

class Pair {
    int row;
    int col;
    public Pair(int x, int y) {
        this.row = x;
        this.col = y;
    }
}