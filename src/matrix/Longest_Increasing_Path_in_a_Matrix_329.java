package matrix;

/*
Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down.

You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

Example 3:

Input: matrix = [[1]]
Output: 1


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1
*/
class Longest_Increasing_Path_in_a_Matrix_329 {
    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[].length : 0;

        int ans = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                ans = Math.max(ans, dfs(matrix, i, j));
        return ans;
    }

    private int dfs(int[][] matrix, int i, int j) {
        int ans = 0;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];

            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[i][j] > matrix[x][y])
                ans = Math.max(ans, dfs(matrix, x, y));
        }

        return ++ans;
    }

    public static void main(String[] args) {
        Longest_Increasing_Path_in_a_Matrix_329 obj = new Longest_Increasing_Path_in_a_Matrix_329();
    }

    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[].length : 0;
        int[][] cache = new int[rows][cols];

        int ans = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                ans = Math.max(ans, dfs(matrix, i, j, cache));
        return ans;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];

        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];

            if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[i][j] > matrix[x][y])
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
        }

        return ++cache[i][j];
    }

    public static void main(String[] args) {
        Longest_Increasing_Path_in_a_Matrix_329 obj = new Longest_Increasing_Path_in_a_Matrix_329();
    }
}

class Longest_Increasing_Path_in_a_Matrix_329 {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int max = 0, m = matrix.length, n = matrix[0].length;

        int[][] cache = new int[m][n];

        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                max = Math.max(dfs(matrix, Integer.MIN_VALUE, i, j, m, n, cache), max);

        return max;
    }

    private int dfs(int[][] matrix, int min, int i, int j, int m, int n, int[][] cache) {
        if(i < 0 || j < 0 || i >= m || j >= n) return 0;
        
        if(matrix[i][j] <= min) return 0;
        
        if(cache[i][j] != 0) return cache[i][j];
        
        min = matrix[i][j];

        int a = dfs(matrix, min, i - 1, j, m, n, cache) + 1;
        int b = dfs(matrix, min, i + 1, j, m, n, cache) + 1;
        int c = dfs(matrix, min, i, j - 1, m, n, cache) + 1;
        int d = dfs(matrix, min, i, j + 1, m, n, cache) + 1;

        int max = Math.max(a, Math.max(b, (Math.max(c, d))));
        cache[i][j] = max;

        return max;
    }
}


class Solution {
    // This is the DFA we have designed above
    private static final List<Map<String, Integer>> dfa = List.of(
        Map.of("digit", 1, "sign", 2, "dot", 3), // State 0
        Map.of("digit", 1, "dot", 4, "exponent", 5),  // State 1
        Map.of("digit", 1, "dot", 3),  // State 2
        Map.of("digit", 4), // State 3
        Map.of("digit", 4, "exponent", 5), // State 4
        Map.of("sign", 6, "digit", 7), // State 5
        Map.of("digit", 7), // State 6
        Map.of("digit", 7) // State 7
    );

    // These are all of the valid finishing states for our DFA.
    private static final Set<Integer> validFinalStates = Set.of(1, 4, 7);

    public boolean isNumber(String s) {
        int currentState = 0;
        String group = "";
        
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                group = "digit";
            } else if (curr == '+' || curr == '-') {
                group = "sign";
            } else if (curr == 'e' || curr == 'E') {
                group = "exponent";
            } else if (curr == '.') {
                group = "dot";
            } else {
                return false;
            }
            
            if (!dfa.get(currentState).containsKey(group)) return false;
            
            
            currentState = dfa.get(currentState).get(group);
        }
        
        return validFinalStates.contains(currentState);
    }
}