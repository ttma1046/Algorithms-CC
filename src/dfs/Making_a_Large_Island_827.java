package dfs;
/*
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.

Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
*/
class Making_a_Large_Island_827 {
    // first
    public int largestIsland(int[][] grid) {
        Map<Integer, Integer> map = new HashMap<>(); //Key: color, Val: size of island painted of that color
        map.put(0, 0); //We won't paint island 0, hence make its size 0, we will use this value later
        int n = grid.length;
        int colorIndex = 2; //0 and 1 is already used in grid, hence we start colorIndex from 2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = paint(grid, i, j, colorIndex);
                    map.put(colorIndex, size);
                    colorIndex++;
                }
            }
        }

        //If there is no island 0 from grid, res should be the size of islands of first color
        //If there is no island 1 from grid, res should be 0
        int res = map.getOrDefault(2, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    //We use a set to avoid repeatly adding islands with the same color
                    Set<Integer> set = new HashSet<>();
                    //If current island is at the boundary, we add 0 to the set, whose value is 0 in the map
                    set.add(i > 0 ? grid[i - 1][j] : 0);
                    set.add(i < n - 1 ? grid[i + 1][j] : 0);
                    set.add(j > 0 ? grid[i][j - 1] : 0);
                    set.add(j < n - 1 ? grid[i][j + 1] : 0);

                    int newSize = 1; //We need to count current island as well, hence we init newSize with 1
                    for (int color : set) newSize += map.get(color);
                    res = Math.max(res, newSize);
                }
            }
        }
        return res;
    }

    //Helper method to paint current island and all its connected neighbors
    //Return the size of all painted islands at the end
    private int paint(int[][] grid, int i, int j, int color) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) return 0;
        grid[i][j] = color;
        return 1 + paint(grid, i + 1, j, color) + paint(grid, i - 1, j, color) + paint(grid, i, j + 1, color) + paint(grid, i, j - 1, color);
    }


    // second
    int curr = 0;
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int[] area = new int[n * n + 2];
        int in = 2;
        int res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    curr = 0;
                    dfs(i, j, grid, in);
                    area[in] = curr;
                    res = Math.max(res, curr);
                    in++;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    int up = i > 0 ? grid[i - 1][j] : 0;
                    int down = i < n - 1 ? grid[i + 1][j] : 0;
                    int left = j > 0 ? grid[i][j - 1] : 0;
                    int right = j < n - 1 ? grid[i][j + 1] : 0;
                    int c = 1;
                    c += area[up];
                    if(up != down) c += area[down];
                    if(left != up && left != down) c += area[left];
                    if(right != up && right != down && right != left) c += area[right];
                    res = Math.max(c, res);
                }
            }
        }

        return res;
    }

    public void dfs(int i, int j, int[][] grid, int c) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid.length || grid[i][j] != 1) return;

        grid[i][j] = c;
        curr++;
        dfs(i - 1, j, grid, c);
        dfs(i, j - 1, grid, c);
        dfs(i + 1, j, grid, c);
        dfs(i, j + 1, grid, c);
    }

    int[][] dirs = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 1;


        DSU dsu = new DSU(m * n);

        for (int i = 0; i < m; ++i) 
            for (int i = 0; i < m; ++i) 
                if (grid[i][j] == 1)
                    for (int[] dir: dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];

                        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != 1) continue;
                        dsu.union(i * n + j, x * n + y);
                        res = Math.max(res, dsu.size[dsu.find(i * n + j)]);
                    }

        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 0) {
                    Map<Integer, Integer> map = new HashMap<>();

                    for (int[] dir: dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];

                        if (x < 0 || x >= m || y < 0  || y >= n || grid[x][y] != 1) continue;

                        int parent = dsu.find(x * n + y);
                        map.put(parent, dsu.size[parent]);
                    }

                    res = Math.max(res, map.values().stream().mapToInt(x -> x).sum() + 1);
                }

        return res;
    }
}
