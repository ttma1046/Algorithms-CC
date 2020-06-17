This is a very typical BFS problem and very staightforward.
We start from all the lands and start exploring the water layer by layer until all the water are explored.
How many layers have we explored? That would be the answer to be returned.
17 ms, faster than 100.00%

class Solution {
    int[][] directions = new int[][] {{-1, 0},{1,0},{0,-1},{0,1}};
    public int maxDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)return -1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> queue = new ArrayDeque<>();
        for(int i = 0 ; i < grid.length ; i++) {
            for(int j = 0 ; j < grid[0].length ; j++) {
                if(grid[i][j] == 1) {
                    queue.offer(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        int level = -1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0 ; i < size ; i++) {
                int[] start = queue.poll();
                int x = start[0];
                int y = start[1];
                for(int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length 
                       && !visited[newX][newY] && grid[newX][newY] == 0) {
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
            level++;
        }
        return level <= 0 ? -1 : level;
    }
}