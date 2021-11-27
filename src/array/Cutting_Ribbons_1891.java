package array;

/*
You are given an integer array ribbons,

where ribbons[i] represents the length of the ith ribbon, and an integer k.

You may cut any of the ribbons into any number of segments of positive integer lengths, or perform no cuts at all.

For example, if you have a ribbon of length 4, you can:
Keep the ribbon of length 4,
Cut it into one ribbon of length 3 and one ribbon of length 1,
Cut it into two ribbons of length 2,
Cut it into one ribbon of length 2 and two ribbons of length 1, or
Cut it into four ribbons of length 1.
Your goal is to obtain k ribbons of all the same positive integer length.

You are allowed to throw away any excess ribbon as a result of cutting.

Return the maximum possible positive integer length that you can obtain k ribbons of,

or 0 if you cannot obtain k ribbons of the same length.

Example 1:

Input: ribbons = [9,7,5], k = 3
Output: 5
Explanation:
- Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
- Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
- Keep the third ribbon as it is.
Now you have 3 ribbons of length 5.

Example 2:

Input: ribbons = [7,5,9], k = 4
Output: 4
Explanation:
- Cut the first ribbon to two ribbons, one of length 4 and one of length 3.
- Cut the second ribbon to two ribbons, one of length 4 and one of length 1.
- Cut the third ribbon to three ribbons, two of length 4 and one of length 1.
Now you have 4 ribbons of length 4.

Example 3:

Input: ribbons = [5,7,9], k = 22
Output: 0
Explanation: You cannot obtain k ribbons of the same positive integer length.

Constraints:

1 <= ribbons.length <= 105
1 <= ribbons[i] <= 105
1 <= k <= 109
*/
class Solution {
    public int maxLength(int[] ribbons, int k) {
        int max = Integer.MIN_VALUE;
        for(int len : ribbons)
            max = Math.max(max, len);

        int n = ribbons.length, low = 1, high = max, mid, count;
        while(low <= high) {
            mid = low + (high - low) / 2;

            count = 0;
            for(int length : ribbons)
                count += length / mid;

            if(count < k)
                high = mid - 1;
            else
                low = mid + 1;
        }

        return high > 0 ? high : 0;
    }

    /*
    public boolean isCutPossible(int[] ribbons, int length, int k) {
        int count = 0;
        for (int ribbon : ribbons) {
            count += (ribbon / length);
        } // I could've written an early 'return' here to save some computation, but for me, the more "if", the more likely to bug
        return count >= k;
    }
    */
}


class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    /**
    * O(mn)^2, O(mn)
    * 1. 对箱子走BFS，四个方向移动箱子，确定箱子可能的下一个位置（nextBoxX，nextBoxY）
    * 2. 根据箱子的下一个位置，计算人需要在哪个位置（nextPX，nextPY）才能推动箱子到下一个位置（nextBoxX，nextBoxY）
    * 3. 再判断人从原来的位置（peopleX，peopleY） 能够达到（nextPX，nextPY），
    * 4. 如果能到达，说明箱子就一定能从（boxX，boxY）移动到（nextBoxX，nextBoxY）
    */
    public int minPushBox(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int step = 0;
        boolean[][][] visited = new boolean[m][n][4];

        Queue<Pair[]> queue = new LinkedList<>();
        Pair box = new Pair(-1, -1), target = new Pair(-1, -1), player = new Pair(-1, -1);
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
                if (curr[0].x == target.x && curr[0].y == target.y) {
                    return step;
                }

                for (int j = 0; j < 4; j++) {
                    if (visited[curr[0].x][curr[0].y][j]) {
                        continue;
                    }

                    int r0 = curr[0].x + dx[j], c0 = curr[0].y + dy[j];  // where pl stands, have room to push;
                    if (r0 < 0 || r0 >= m || c0 < 0 || c0 >= n || grid[r0][c0] == '#') {
                        continue;
                    }

                    int r = curr[0].x - dx[j], c = curr[0].y - dy[j];  // box next spots;
                    if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '#') {
                        continue;
                    }

                    if (!reachable(grid, r0, c0, curr)) { // player reachable?
                        continue;
                    }

                    visited[curr[0].x][curr[0].y][j] = true;
                    queue.offer(new Pair[] {new Pair(r, c), new Pair(curr[0].x, curr[0].y)});
                }
            }
            step++;
        }
        return -1;
    }

    private boolean reachable(char[][] grid, int x, int y, Pair[] curr) {
        int m = grid.length;
        int n = grid[0].length;
        Pair player = curr[1];
        Pair box = curr[0];
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(player);
        boolean[][] visited = new boolean[m][n];
        visited[box.x][box.y] = true;
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            if (cur.x == x && cur.y == y) return true;
            for (int k = 0; k < 4; k++) {
                int r = cur.x - dx[k], c = cur.y - dy[k];  // box next spots;
                if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c] || grid[r][c] == '#') continue;
                visited[r][c] = true;
                queue.offer(new Pair(r, c));
            }
        }
        return false;
    }
}

class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}