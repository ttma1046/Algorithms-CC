package dfs;

class number_of_islands_200 {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }

        int rowLength = grid.length;
        int columnLength = grid[0].length;

        boolean[][] visited = new boolean[rowLength][columnLength];
        int result = 0;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    result++;
                    countIslands(grid, i, j, rowLength, columnLength, visited);
                }
            }
        }

        return result;
    }

    private void countIslands(char[][] grid, int i, int j, int rowLength, int columnLength, boolean[][] visited) {
        if (i > rowLength - 1 || j > columnLength - 1 || i < 0 || j < 0) {
            return;
        }

        if (grid[i][j] == '1' && visited[i][j] == false) {
            visited[i][j] = true;
            countIslands(grid, i + 1, j, rowLength, columnLength, visited);
            countIslands(grid, i, j + 1, rowLength, columnLength, visited);
            countIslands(grid, i - 1, j, rowLength, columnLength, visited);
            countIslands(grid, i, j - 1, rowLength, columnLength, visited);
        }
    }

    public static void main(String[] args) {
        char[][] ocean = new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' } };
        System.out.println(new number_of_islands_200().numIslands(ocean));
        ocean = new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' } };
        System.out.println(new number_of_islands_200().numIslands(ocean));
    }
}