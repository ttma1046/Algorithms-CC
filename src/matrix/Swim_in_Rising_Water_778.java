package matrix;

/*
You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

Example 1:

Input: grid = [
   [0,2],
   [1,3]
 ]

Output: 3

Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.

Example 2:

Input: grid = [
    [ 0, 1, 2, 3, 4],
    [24,23,22,21, 5],
    [12,13,14,15,16],
    [11,17,18,19,20],
    [10, 9, 8, 7, 6]
]

Output: 16
Explanation: The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 50
0 <= grid[i][j] < n2
Each value grid[i][j] is unique.
*/
class Swim_in_Rising_Water_778 {
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        int lo = grid[0][0], hi = N * N;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (!possible(mi, grid)) {
                lo = mi + 1;
            } else {
                hi = mi;
            }
        }
        return lo;
    }

    public boolean possible(int T, int[][] grid) {
        int N = grid.length;
        Set<Integer> seen = new HashSet();
        seen.add(0);
        int[] dr = new int[] {1, -1, 0, 0};
        int[] dc = new int[] {0, 0, 1, -1};

        Stack<Integer> stack = new Stack();
        stack.add(0);

        while (!stack.empty()) {
            int k = stack.pop();
            int r = k / N, c = k % N;
            if (r == N - 1 && c == N - 1) return true;

            for (int i = 0; i < 4; ++i) {
                int cr = r + dr[i], cc = c + dc[i];
                int ck = cr * N + cc;
                if (0 <= cr && cr < N && 0 <= cc && cc < N
                        && !seen.contains(ck) && grid[cr][cc] <= T) {
                    stack.add(ck);
                    seen.add(ck);
                }
            }
        }

        return false;
    }

    private int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private int[] parents;
    private int[] sizes;

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        buildUnionFind(n);
        int[] map = new int[n * n];


        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                map[grid[i][j]] = i * n + j;

        for (int time = 0; time < n * n; time++) {
            int index = map[time];
            int row = index / n;
            int col = index % n;

            for (int[] direction: directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= n || grid[newRow][newCol] >= time)
                    continue;

                union(index, newRow * n + newCol);
            }

            if (connected(0, n * n - 1))
                return time;
        }

        return -1;
    }

    private void buildUnionFind(int n) {
        parents = new int[n * n];
        sizes= new int[n * n];
        
        for (int i = 0 ; i < n * n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
    }

    private int find(int x) {
        while(x != parents[x]) {
            parents[x] = parents[parents[x]];
            x = parents[x];
        }

        return x;
    }

    private int find(int x) {
        if(x != parents[x])
            parents[x] = find(parents[x]);
        
        return parents[x];
    }

    private boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) 
            return;

        if (sizes[rootX] <= sizes[rootY]) {
            parents[rootX] = rootY;
            sizes[rootY] += sizes[rootX];
        } else {
            parents[rootY] = rootX;
            sizes[rootY] += sizes[rootX];
        }
    }

}