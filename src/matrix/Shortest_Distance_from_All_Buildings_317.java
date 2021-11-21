package matrix;

/*
You are given an m x n grid grid of values 0, 1, or 2, where:

each 0 marks an empty land that you can pass by freely,
each 1 marks a building that you cannot pass through, and
each 2 marks an obstacle that you cannot pass through.
You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.

Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.

The total travel distance is the sum of the distances between the houses of the friends and the meeting point.

The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example 1:

Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
Output: 7
Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
So return 7.

Example 2:

Input: grid = [[1,0]]
Output: 1

Example 3:

Input: grid = [[1]]
Output: -1

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0, 1, or 2.
There will be at least one building in the grid.
*/
class Shortest_Distance_from_All_Building_317 {
    public int shortestDistance(int[][] grid) {
<<<<<<< HEAD
        int rows = grid.length;
        int cols = grid[0].length;

        int totalHouses = 0;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 1) totalHouses++;
            }
        }

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 0) minDistance = Math.min(minDistance, bfs(grid, row, col, totalHouses));
            }
        }

        if (minDistance == Integer.MAX_VALUE) return -1;

        return 
    }

    private boolean bfs(int[][] grid, int i, int j, int housesReached) {
        int[][] dirs = new int[] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        int rows = grid.length;
        int cols = grid[0].length;

        int distanceSum = 0;
        int housesReached = 0;

        Queue queue = new LinkedList();

        queue.add(new int[] {i, j});

        boolean[][] vis = new boolean[rows][cols];
        vis[row][col] = true;

        int step = 0;
        while(queue.size() > 0 && housesReached != totalHouses) {
            for (int t = queue.size(); t > 0; --t) {
                int[] location = queue.poll();
                row = location[0];
                col = location[1];

                // and we go past from this cell.
                if (grid[row][col] == 1) {
                    distanceSum += steps;
                    housesReached++;
                    continue;
                }

                for (int k = 0; i < dirs.length; i++) {
                    int newX = row + dir[i][0];
                    int newY = col + dir[i][1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                        if (!vis[newX][newY] && grid[newX][newY] != 2) {
                            vis[newX][newY] = true;
                            queue.offer(new int[] { newX, newY });
                        }
                    }
                }
            }

            step++;
        }
    }
}

class Solution {
    public int shortestDistance(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        int rows = grid.length;
        int cols = grid[0].length;
        int totalHouses = 0;
=======
        if (grid == null || grid[0].length == 0) return 0;
        final int[] shift = new int[] {0, 1, 0, -1, 0};
        
        int row  = grid.length, col = grid[0].length;
        int[][] distance = new int[row][col];
        int[][] reach = new int[row][col];
        int buildingNum = 0;
        
        for (int i = 0; i < row; i++) {
            for (int j =0; j < col; j++) {
                if (grid[i][j] == 1) {
                    buildingNum++;
                    Queue<int[]> myQueue = new LinkedList<int[]>();
                    myQueue.offer(new int[] {i,j});
>>>>>>> 2d338295940e4f4a44d426bf6f27959545806df1

                    boolean[][] isVisited = new boolean[row][col];
                    int level = 1;
                    
                    while (!myQueue.isEmpty()) {
                        int qSize = myQueue.size();
                        for (int q = 0; q < qSize; q++) {
                            int[] curr = myQueue.poll();
                            
                            for (int k = 0; k < 4; k++) {
                                int nextRow = curr[0] + shift[k];
                                int nextCol = curr[1] + shift[k + 1];
                                
                                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                                    && grid[nextRow][nextCol] == 0 && !isVisited[nextRow][nextCol]) {
                                        //The shortest distance from [nextRow][nextCol] to thic building
                                        // is 'level'.
                                        distance[nextRow][nextCol] += level;
                                        reach[nextRow][nextCol]++;
                                        
                                        isVisited[nextRow][nextCol] = true;
                                        myQueue.offer(new int[] {nextRow, nextCol});
                                    }
                            }
                        }
                        level++;
                    }
                }
            }
        }
        
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
                    shortest = Math.min(shortest, distance[i][j]);
                }
            }
        }
<<<<<<< HEAD

        // If it is impossible to reach all houses from any empty cell, then return -1.
        if (minDistance == Integer.MAX_VALUE) return -1;

        return minDistance;
=======
        
        return shortest == Integer.MAX_VALUE ? -1 : shortest;    
>>>>>>> 2d338295940e4f4a44d426bf6f27959545806df1
    }

    private int bfs(int[][] grid, int row, int col, int totalHouses) {
        // Next four directions.
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int rows = grid.length;
        int cols = grid[0].length;
        int distanceSum = 0;
        int housesReached = 0;

        // Queue to do a bfs, starting from (row, col) cell.
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { row, col });

        // Keep track of visited cells.
        boolean[][] vis = new boolean[rows][cols];
        vis[row][col] = true;

        int steps = 0;
        while (!q.isEmpty() && housesReached != totalHouses) {
            for (int i = q.size(); i > 0; --i) {
                int[] curr = q.poll();
                row = curr[0];
                col = curr[1];

                // If this cell is a house, then add the distance from source to this cell
                // and we go past from this cell.
                if (grid[row][col] == 1) {
                    distanceSum += steps;
                    housesReached++;
                    continue;
                }

                // This cell was empty cell, hence traverse the next cells which is not a blockage.
                for (int[] dir : dirs) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];
                    if (nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols) {
                        if (!vis[nextRow][nextCol] && grid[nextRow][nextCol] != 2) {
                            vis[nextRow][nextCol] = true;
                            q.offer(new int[] { nextRow, nextCol });
                        }
                    }
                }
            }

            // After traversing one level of cells, increment the steps by 1 to reach to next level.
            steps++;
        }

        // If we did not reach all houses, then any cell visited also cannot reach all houses.
        // Set all cells visted to 2 so we do not check them again and return MAX_VALUE.
        if (housesReached != totalHouses) {
            for (row = 0; row < rows; row++) {
                for (col = 0; col < cols; col++) {
                    if (grid[row][col] == 0 && vis[row][col]) {
                        grid[row][col] = 2;
                    }
                }
            }
            return Integer.MAX_VALUE;
        }

        // If we have reached all houses then return the total distance calculated.
        return distanceSum;
    }
}
/*
Let N and M be the number of rows and columns in grid respectively.

Time Complexity: O(N^2 * M^2)

For each empty land, we will traverse to all other houses.

This will require O(O(number of zeros * number of ones)) time and the number of zeros and ones in the matrix is of order N \cdot MN⋅M.

Consider that when half of the values in grid are 0 and half of the values are 1, the total elements in grid would be (M \cdot N)(M⋅N) so their counts are (M \cdot N)/2(M⋅N)/2 and (M \cdot N)/2(M⋅N)/2 respectively,

thus giving time complexity (M \cdot N)/2 \cdot (M \cdot N)/2(M⋅N)/2⋅(M⋅N)/2, that results in O(N^2 \cdot M^2)

In JavaScript implementation, for simplicity, we have used an array for the queue. Since popping elements from the front of an array is an O(n) operation, which is undesirable, instead of popping from the front of the queue, we will iterate over the queue and record cells to be explored in the next level in next_queue. Once the current queue has been traversed, we simply set queue equal to the next_queue.

Space Complexity: O(N⋅M)

We use an extra matrix to track the visited cells, and the queue will store each matrix element at most once during each BFS call. Hence, O(N⋅M) space is required.
*/
class Solution {
    private void bfs(int[][] grid, int[][][] distances, int row, int col) {
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int rows = grid.length;
        int cols = grid[0].length;

        // Use a queue to do a bfs, starting from each cell located at (row, col).
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] { row, col });

        // Keep track of visited cells.
        boolean[][] vis = new boolean[rows][cols];
        vis[row][col] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                int[] curr = q.poll();
                row = curr[0];
                col = curr[1];

                // If we reached an empty cell, then add the distance
                // and increment the count of houses reached at this cell.
                if (grid[row][col] == 0) {
                    distances[row][col][0] += steps;
                    distances[row][col][1] += 1;
                }

                // Traverse the next cells which is not a blockage.
                for (int[] dir : dirs) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (nextRow >= 0 && nextCol >= 0 && nextRow < rows && nextCol < cols) {
                        if (!vis[nextRow][nextCol] && grid[nextRow][nextCol] == 0) {
                            vis[nextRow][nextCol] = true;
                            q.offer(new int[] { nextRow, nextCol });
                        }
                    }
                }
            }

            // After traversing one level cells, increment the steps by 1.
            steps++;
        }
    }

    public int shortestDistance(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;
        int rows = grid.length;
        int cols = grid[0].length;
        int totalHouses = 0;

        // Store { total_dist, houses_count } for each cell.
        int[][][] distances = new int[rows][cols][2];

        // Count houses and start bfs from each house.
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (grid[row][col] == 1) {
                    totalHouses++;
                    bfs(grid, distances, row, col);
                }
            }
        }

        // Check all empty lands with houses count equal to total houses and find the min ans.
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (distances[row][col][1] == totalHouses) {
                    minDistance = Math.min(minDistance, distances[row][col][0]);
                }
            }
        }

        // If we haven't found a valid cell return -1.
        if (minDistance == Integer.MAX_VALUE) {
            return -1;
        }
        return minDistance;
    }
};

/*
Complexity Analysis

Let NN and MM be the number of rows and columns in grid respectively.

Time Complexity: O(N^2 * M^2)

For each house, we will traverse across all reachable land.

This will require O(number of zeros * number of ones) time and the number of zeros

and ones in the matrix is of order N * M.

Consider that when half of the values in grid are 0 and half of the values are 1,

total elements in grid will be (M * N) so their counts are (M * N) / 2 and (M * N)/2 respectively,

thus giving time complexity (M * N)/2 * (M * N)/2, which results in O(N^2 * M^2).

Space Complexity: O(N * M)

We use an extra matrix to track the visited cells and another one to store distance sum

along with the house counter for each empty cell, and the queue will store each matrix element at most once during each BFS call.

Hence, O(N * M) space is required.
*/
class Solution {
    public int shortestDistance(int[][] grid) {
        // Next four directions.
        int dirs[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int rows = grid.length;
        int cols = grid[0].length;

        // Total Mtrix to store total distance sum for each empty cell.
        int[][] total = new int[rows][cols];

        int emptyLandValue = 0;
        int minDist = Integer.MAX_VALUE;

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {

                // Start a BFS from each house.
                if (grid[row][col] == 1) {
                    minDist = Integer.MAX_VALUE;

                    // Use a queue to perform a BFS, starting from the cell at (r, c).
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[] { row, col });

                    int steps = 0;

                    while (!q.isEmpty()) {
                        steps++;

                        for (int level = q.size(); level > 0; --level) {
                            int[] curr = q.poll();

                            for (int[] dir : dirs) {
                                int nextRow = curr[0] + dir[0];
                                int nextCol = curr[1] + dir[1];

                                // For each cell with the value equal to empty land value
                                // add distance and decrement the cell value by 1.
                                if (nextRow >= 0 && nextRow < rows &&
                                        nextCol >= 0 && nextCol < cols &&
                                        grid[nextRow][nextCol] == emptyLandValue) {
                                    grid[nextRow][nextCol]--;
                                    total[nextRow][nextCol] += steps;

                                    q.offer(new int[] { nextRow, nextCol });
                                    minDist = Math.min(minDist, total[nextRow][nextCol]);
                                }
                            }
                        }
                    }

                    // Decrement empty land value to be searched in next iteration.
                    emptyLandValue--;
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
/*
Complexity Analysis

Let N and M be the number of rows and columns in grid respectively.

Time Complexity: O(N^2 * M^2)

For each house, we will traverse across all reachable land.

This will require O(number of zeros * number of ones) time and the number of zeros and ones

in the matrix is of order N * M.

Consider that when half of the values in grid are 0 and half of the values are 1,

total elements in grid would be N * M

so their counts are (M * N)/2 and (M * N)/2 respectively,

thus giving time complexity (M * N)/2 * (M * N)/2, that results in O(N^2 * M^2).

Space Complexity: O(N * M)

We use an extra matrix to store distance sums, and the queue will store each matrix element at most once during each BFS call. Hence, O(N * M) space is required.
*/

class Solution {
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<tuple> buildings = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    buildings.add(new tuple(i, j, 0));

                grid[i][j] = -grid[i][j];
            }
        }

        int[][] result = new int[m][n];

        for (int i = 0; i < buildings.size(); i++)
            bfs(buildings.get(i), i, grid, result);

        int shortest = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == buildings.size()
                        && (shortest == -1 || shortest > result[i][j]))
                    shortest = result[i][j];
            }
        }

        return shortest;
    }

    private void bfs(tuple cur, int k, int[][] grid, int[][] result) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<tuple> q = new ArrayDeque<>();
        q.add(cur);
        while (!q.isEmpty()) {
            cur = q.poll();
            int x = cur.x, y = cur.y, dist = cur.dist;
            for (int i = 0; i < 4; i++) {
                int newX = x + dirs[i][0];
                int newY = y + dirs[i][1];
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length
                        && grid[newX][newY] == k) {
                    grid[newX][newY] = k + 1;
                    result[newX][newY] += dist + 1;
                    q.offer(new tuple(newX, newY, dist + 1));
                }
            }
        }
    }

    class tuple {
        int row;
        int col;
        int dist;
        tuple(int i, int j, int c) {
            row = i;
            col = j;
            dist = c;
        }
    }
}

/*
Inspired by previous solution.
The main idea is the following:

Traverse the matrix. For each building, use BFS to compute the shortest distance from each '0' to
this building. After we do this for all the buildings, we can get the sum of shortest distance
from every '0' to all reachable buildings. This value is stored
in 'distance[][]'. For example, if grid[2][2] == 0, distance[2][2] is the sum of shortest distance from this block to all reachable buildings.
Time complexity: O(number of 1)O(number of 0) ~ O(m^2n^2)

We also count how many building each '0' can be reached. It is stored in reach[][]. This can be done during the BFS. We also need to count how many total buildings are there in the matrix, which is stored in 'buildingNum'.

Finally, we can traverse the distance[][] matrix to get the point having shortest distance to all buildings. O(m*n)

The total time complexity will be O(m^2*n^2), which is quite high!. Please let me know if I did the analysis wrong or you have better solution.
*/
public class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid[0].length == 0) return 0;
        final int[] shift = new int[] {0, 1, 0, -1, 0};

        int row  = grid.length, col = grid[0].length;
        int[][] distance = new int[row][col];
        int[][] reach = new int[row][col];
        int buildingNum = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    buildingNum++;
                    Queue<int[]> myQueue = new LinkedList<int[]>();
                    myQueue.offer(new int[] {i, j});

                    boolean[][] isVisited = new boolean[row][col];
                    int level = 1;

                    while (!myQueue.isEmpty()) {
                        int qSize = myQueue.size();
                        for (int q = 0; q < qSize; q++) {
                            int[] curr = myQueue.poll();

                            for (int k = 0; k < 4; k++) {
                                int nextRow = curr[0] + shift[k];
                                int nextCol = curr[1] + shift[k + 1];

                                if (nextRow >= 0 && nextRow < row && nextCol >= 0 && nextCol < col
                                        && grid[nextRow][nextCol] == 0 && !isVisited[nextRow][nextCol]) {
                                    //The shortest distance from [nextRow][nextCol] to thic building
                                    // is 'level'.
                                    distance[nextRow][nextCol] += level;
                                    reach[nextRow][nextCol]++;

                                    isVisited[nextRow][nextCol] = true;
                                    myQueue.offer(new int[] {nextRow, nextCol});
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }

        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildingNum) {
                    shortest = Math.min(shortest, distance[i][j]);
                }
            }
        }

        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }
}