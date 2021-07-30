package dp;
import java.util.Arrays;
/*
174. Dungeon Game

The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room and must fight his way through dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons (represented by negative integers), so the knight loses health upon entering these rooms; other rooms are either empty (represented as 0) or contain magic orbs that increase the knight's health (represented by positive integers).

To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.

Return the knight's minimum initial health so that he can rescue the princess.

Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.

Example 1:


Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
Output: 7
Explanation: The initial health of the knight must be at least 7 if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.

Example 2:

Input: dungeon = [[0]]
Output: 1

Constraints:

m == dungeon.length
n == dungeon[i].length
1 <= m, n <= 200
-1000 <= dungeon[i][j] <= 1000
*/

class Dungeon_Game_174 {
    public int getMinHealth(int currCell, int nextRow, int nextCol, int[][] dp, int rows, int cols) {
        if (nextRow >= rows || nextCol >= cols) return Integer.MAX_VALUE;

        int nextCell = dp[nextRow][nextCol];
        return Math.max(1, nextCell - currCell);
    }

    public int calculateMinimumHPII(int[][] dungeon) {
        int rows = dungeon.length;
        int cols = dungeon[0].length;

        int[][] dp = new int[rows][cols];

        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        int currCell, rightHealth, downHealth, minHealth;
        for (int row = rows - 1; row >= 0; --row) {
            for (int col = cols - 1; col >= 0; --col) {
                currCell = dungeon[row][col];

                rightHealth = getMinHealth(currCell, row, col + 1, dp, rows, cols);
                downHealth = getMinHealth(currCell, row + 1, col, dp, rows, cols);

                if (Math.min(rightHealth, downHealth) != Integer.MAX_VALUE) {
                    minHealth = Math.min(rightHealth, downHealth);
                } else {
                    minHealth = currCell >= 0 ? 1 : 1 - currCell;
                }

                dp[row][col] = minHealth;
            }
        }

        /*
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                System.out.print(dp[i][j] + ",");
            }

            System.out.println();
        }
        */

        return dp[0][0];
    }

    /*
    Time Complexity: O(M⋅N) where M⋅N is the size of the dungeon. We iterate through the entire dungeon once and only once.

    Space Complexity: O(M⋅N) where M⋅N is the size of the dungeon. In the algorithm, we keep a dp matrix that is of the same size as the dungeon.
    */

    class MyCircularQueue {
        protected int capacity;
        protected int tailIndex;
        public int[] queue;

        public MyCircularQueue(int capacity) {
            this.queue = new int[capacity];
            this.tailIndex = 0;
            this.capacity = capacity;
        }

        public void enQueue(int value) {
            this.queue[this.tailIndex] = value;
            this.tailIndex = (this.tailIndex + 1) % this.capacity;
        }

        public int get(int index) {
            return this.queue[index % this.capacity];
        }
    }

    int inf = Integer.MAX_VALUE;
    MyCircularQueue dp;
    int rows, cols;

    public int getMinHealth(int currCell, int nextRow, int nextCol) {
        if (nextRow < 0 || nextCol < 0)
            return inf;

        int index = cols * nextRow + nextCol;
        int nextCell = this.dp.get(index);
        // hero needs at least 1 point to survive
        return Math.max(1, nextCell - currCell);
    }

    public int calculateMinimumHPIII(int[][] dungeon) {
        this.rows = dungeon.length;
        this.cols = dungeon[0].length;
        this.dp = new MyCircularQueue(this.cols);

        int currCell, rightHealth, downHealth, nextHealth, minHealth;
        for (int row = 0; row < this.rows; ++row) {
            for (int col = 0; col < this.cols; ++col) {
                currCell = dungeon[rows - row - 1][cols - col - 1];

                rightHealth = getMinHealth(currCell, row, col - 1);
                downHealth = getMinHealth(currCell, row - 1, col);
                nextHealth = Math.min(rightHealth, downHealth);

                if (nextHealth != inf) {
                    minHealth = nextHealth;
                } else {
                    minHealth = currCell >= 0 ? 1 : 1 - currCell;
                }
                this.dp.enQueue(minHealth);
            }
        }

        // retrieve the last element in the queue
        return this.dp.get(this.cols - 1);
    }


    public static void main(String[] args) {
        int[][] dungeon = new int[][] {{ -2, -3, 3}, { -5, -10, 1}, {10, 30, -5}};

        Dungeon_Game_174 obj = new Dungeon_Game_174();

        System.out.println(obj.calculateMinimumHP(dungeon));
    }

    /*
    Complexity:
    Time Complexity: \mathcal{O}(M \cdot N)O(M⋅N) where M \cdot NM⋅N is the size of the dungeon. We iterate through the entire dungeon once and only once.

    Space Complexity: \mathcal{O}(N)O(N) where NN is the number of columns in the dungeon.
    */

    int getValRes(int[][] mat, int i, int j) {
        int n = mat.length;
        int m = mat[0].length;

        // Base case : we have crossed the matrix, ie. out of bound
        /// if current row crosses then my row is below the princess or
        /// if current column crosses then my column is ahead the column of princess
        /// and beacause we can go only down and right so we wont be able reach princess
        if(i == n || j == m)    return Integer.MAX_VALUE;

        // Base Case : we have reached our destination ie. last cell
        /// we reached princess , cheers return this cost;
        if(i == n - 1 && j == m - 1)
            return (mat[i][j] <= 0) ? -mat[i][j] + 1 : 1;

        /// now we must try all possible paths , we ask our right and and down cell
        int IfWeGoRight = getValRes(mat, i, j + 1);
        int IfWeGoDown = getValRes(mat, i + 1, j);

        /// min of either values and then cost of this cell
        int minHealthRequired = Math.min(IfWeGoRight, IfWeGoDown) - mat[i][j];

        /// point 2 as explained
        return (minHealthRequired <= 0) ? 1 : minHealthRequired;
    }

    int calculateMinimumHPIV(int[][] dungeon) {
        return getValRes(dungeon, 0, 0);
    }

    public int calculateMinimumHPV(int[][] dungeon) {
        int rows = dungeon.length;
        int cols = dungeon[0].length;

        int[][] mem = new int[rows][cols];

        for (int[] arr : mem) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        return getVal(dungeon, 0, 0, mem, rows, cols);
    }

    public int getVal(int[][] mat, int i, int j, int[][] mem, int rows, int cols) {
        if (i == rows || j == cols) return Integer.MAX_VALUE;
        if (i == rows - 1 && j == cols - 1) {
            return mat[i][j] < 0 ? -mat[i][j] + 1 : 1;
        }

        if (mem[i][j] != Integer.MAX_VALUE) {
            return mem[i][j];
        }

        int goRight = getVal(mat, i, j + 1, mem, rows, cols);
        int goDown = getVal(mat, i + 1, j, mem, rows, cols);
        int minHpRequired = Math.min(goRight, goDown) - mat[i][j];

        mem[i][j] = minHpRequired > 0 ? minHpRequired : 1;

        return mem[i][j];
    }

    int calculateMinimumHPVI(int[][] dungeon) {
        int rows = dungeon.length;
        int cols = dungeon[0].length;

        int[][] dp = new int[rows + 1][cols + 1];

        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        dp[rows][cols - 1] = 1;
        dp[rows - 1][cols] = 1;

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                int need = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                // store this value
                dp[i][j] = need <= 0 ? 1 : need;
            }
        }

        return dp[0][0];
    }

    /*
    opt(0, 0) = Math.min(opt(1, 0), opt(0, 1)) - dungeon(0, 0);

    opt(i, j) + dungeon[i][j] = Math.min(opt(i + 1, j), opt(i, j + 1));


    opt(1, 0) = opt(1, 1) − dungeon[1][0] = 7

    opt(0, 1) = opt(1, 1) - dungeon[0][1] = 8

    opt(0, 0) = min(opt(1, 0), opt(0, 1)) - dungeon[0][0] = 8

    ans = opt(0, 0) + 1;
    */

    /*
    -1 -5

     4 -3
    */

    /*
    opt(i, j) =
        Math.min(opt(i + 1, j), opt(i, j + 1)) - dungeon[i][j] >= 0 ?
            Math.min(opt(i + 1, j), opt(i, j + 1)) - dungeon[i][j] : 0;
    */

    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;

        int rowLen = dungeon.length;
        int colLen = dungeon[0].length;

        return rec(0, 0, rowLen, colLen, dungeon) + 1;
    }

    int rec(int rowIndex, int colIndex, int rowLen, int colLen, int[][] dungeon) {
        if (rowIndex == rowLen - 1 && colIndex == colLen - 1)
            return dungeon[rowIndex][colIndex] >= 0 ? 0 : -dungeon[rowIndex][colIndex];

        if (rowIndex >= rowLen || colIndex >= colLen) return Integer.MAX_VALUE;

        int right = rec(rowIndex, colIndex + 1, rowLen, colLen, dungeon);
        int down = rec(rowIndex + 1, colIndex, rowLen, colLen, dungeon);

        int res = Math.min(right, down) - dungeon[rowIndex][colIndex];

        return res >= 0 ? res : 0;
    }

    public int calculateMinimumHPVII(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        int rowLen = dungeon.length;
        int colLen = dungeon[0].length;

        return dfs(0, 0, rowLen, colLen, dungeon) + 1;
    }

    public int dfs(int rowIndex, int colIndex, int rowSize, int colSize, int[][] dungeon) {
        //
        if (rowIndex >= rowSize || colIndex >= colSize) {
            return Integer.MAX_VALUE;
        }

        if (rowIndex == rowSize - 1 && colIndex == colSize - 1) {
            if (dungeon[rowIndex][colIndex] >= 0) {
                return 0;
            } else {
                return -dungeon[rowIndex][colIndex];
            }
        }

        int rightMin = dfs(rowIndex, colIndex + 1, rowSize, colSize, dungeon);

        int downMin = dfs(rowIndex + 1, colIndex, rowSize, colSize, dungeon);
        // f(i,j) = min(f(i+1, j), f(i, j+1)) - dungeon[i][j]
        int needMin = Math.min(rightMin, downMin) - dungeon[rowIndex][colIndex];
        int res = 0;
        if (needMin < 0) {
            res =  0;
        } else {
            res = needMin;
        }
        System.out.println(rowIndex + " " + colIndex + " "  + res);
        return res;
    }
}