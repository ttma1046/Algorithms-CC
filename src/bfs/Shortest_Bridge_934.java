package bfs;
import java.util.Queue;
import java.util.LinkedList;

/*
In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

Example 1:
Input: A = [
            [0,1],
            [1,0]
           ]
Output: 1

Example 2:
Input: A = [
             [0,1,0],
             [0,0,0],
             [0,0,1]
           ]
Output: 2

Example 3:
Input: A = [
            [1,1,1,1,1],
            [1,0,0,0,1],
            [1,0,1,0,1],
            [1,0,0,0,1],
            [1,1,1,1,1]
           ]
Output: 1


Constraints:

2 <= A.length == A[0].length <= 100
A[i][j] == 0 or A[i][j] == 1
Accepted
*/
class Shortest_Bridge_934 {
    public int shortestBridgeMy(int[][] A) {
        if (A == null || A.length == 0) return 0;        

        int rows = A.length, columns = A[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> myQueue = new LinkedList<int[]>();

        boolean foundTheFirstIsland = false;

        for(int i = 0; i < rows; i++) {
            if (foundTheFirstIsland) break;            

            for (int j = 0; j < columns; j++) {
                if (A[i][j] == 1 && !foundTheFirstIsland) {
                    dfs(i, j, visited, A, myQueue, directions);
                    foundTheFirstIsland = true;
                    break;
                }
            }
        }

        int result = 0;
        while(myQueue.size() > 0) {
            int queueSize = myQueue.size();
            while(queueSize-- > 0) {
                int[] cur = myQueue.poll();

                for(int[] dir : directions) {
                    int i = cur[0] + dir[0];
                    int j = cur[1] + dir[1];

                    if (i >= 0 && j >= 0 && i < rows && j < columns && !visited[i][j]) {
                        if (A[i][j] == 1) return result;                  
                        myQueue.offer(new int[] {i, j});
                        visited[i][j] = true;
                    }
                }
            }
            result++;
        }

        return -1;
    }

    private void dfs(int i, int j, boolean[][] visited, int[][] A, Queue<int[]> myQueue, int[][] directions) {
        if (i < 0 || j < 0 || i >= A.length || j >= A[0].length || visited[i][j] || A[i][j] == 0) return;
        visited[i][j] = true;
        myQueue.offer(new int[] {i, j});
        for(int[] dir : directions) dfs(i + dir[0], j + dir[1], visited, A, myQueue, directions);        
    }

    // 因为题目中只说了有两座岛屿不是n座，所以遇到下一个1就可以直接return结果了
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    Queue<int[]> q = new LinkedList<>();
    public int shortestBridgeGucheng(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        boolean found = false;

        // 1. 把第一座岛都标记成visitied, 并且放进queue
        for (int i = 0; i < m; ++i) 
            for (int j = 0; j < n; ++j)
                if (!found && grid[i][j] == 1) {
                    dfs(grid, visited, i, j);
                    found = true;
                    break;
                }

        // 2. 从起点q中BFS, 一层一层的扩展自己，直到找到下一个岛屿1，扩展的层数就是步数
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int x = 0; x < size; ++x) {
                int[] curr = q.poll();
                for (int[] dir: dirs) {
                    int i = curr[0] + dir[0]
                    int j = curr[1] + dir[1];
                    if (i >= 0 && j >= 0 && i < m && j < n && !visited[i][j]) {
                        if (grid[i][j] == 1) return step;
                        q.offer(new int[] { i, j });
                        visited[i][j] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    // 把第一座岛都标记为visited, 并且放进queue
    private void dfs(int i, int j, boolean[][] visited, int[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j] || grid[i][j] == 0) return;
        visited[i][j] = true;
        myQueue.offer(new int[] {i, j});
        for(int[] dir : directions) dfs(i + dir[0], j + dir[1], visited, grid);        
    }

    public static void main(String[] args) {
        System.out.println(new Shortest_Bridge_934().shortestBridgeMy(new int[][] {
            {0, 1},
            {1, 0}
        }));

        System.out.println(new Shortest_Bridge_934().shortestBridgeMy(new int[][] {
            {0, 1, 0},
            {0, 0, 0},
            {0, 0, 1}
        }));

        System.out.println(new Shortest_Bridge_934().shortestBridgeMy(new int[][] {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
        }));
    }

    public int shortestBridge(int[][] A) {
        int rows = A.length;
        int columns = A[0].length;

        boolean[][] visited = new boolean[rows][columns];

        int[][] dirs = new int[][] {{1, 0}, { -1, 0}, {0, 1}, {0, -1}};

        Queue<int[]> q = new LinkedList<int[]>();
        boolean found = false;

        // 1. dfs to find an island, mark it in `visited`
        for (int i = 0; i < rows; i++) {
            if (found) break;
            
            for (int j = 0; j < columns; j++) {
                if (A[i][j] == 1 && !found) {
                    dfsII(A, visited, q, i, j, dirs);
                    found = true;
                    break;
                }
            }
        }

        // 2. bfs to expand this island
        int step = 0;
        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int i = cur[0] + dir[0];
                    int j = cur[1] + dir[1];
                    if (i >= 0 && j >= 0 && i < rows && j < columns && !visited[i][j]) {
                        if (A[i][j] == 1) return step;
                        
                        q.offer(new int[] {i, j});
                        visited[i][j] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private void dfsII(int[][] A, boolean[][] visited, Queue<int[]> myQueue, int i, int j, int[][] directions) {
        if (i < 0 || j < 0 || i >= A.length || j >= A[0].length || visited[i][j] || A[i][j] == 0) return;
        visited[i][j] = true;
        myQueue.offer(new int[] {i, j});
        for(int[] dir : directions) dfsII(A, visited, myQueue, i + dir[0], j + dir[1], directions);        
    }
}