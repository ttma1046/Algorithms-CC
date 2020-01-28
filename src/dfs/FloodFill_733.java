package dfs;

public class FloodFill_733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        if (image == null || image.length <=0 || image[0].length <=0) {
            return new int[0][0];
        }

        boolean[][] visited = new boolean[image.length][image[0].length];
        paint(image, sr, sc, newColor, visited);

        return image;
    }

    private void paint(int[][] image, int sr, int sc, int newColor, boolean[][] visited) {
        visited[sr][sc] = true;
        if (sr - 1 >= 0 && !visited[sr - 1][sc] && image[sr][sc] == image[sr - 1][sc])   {
            paint(image, sr - 1, sc, newColor, visited);
        }

        if (sr + 1 < image.length && !visited[sr + 1][sc] && image[sr][sc] == image[sr + 1][sc])   {
            paint(image, sr + 1, sc, newColor, visited);
        }

        if (sc - 1 >= 0 && !visited[sr][sc - 1] && image[sr][sc] == image[sr][sc - 1])   {
            paint(image, sr, sc - 1, newColor, visited);
        }

        if (sc + 1 < image[0].length && !visited[sr][sc + 1] && image[sr][sc] == image[sr][sc + 1])   {
            paint(image, sr, sc + 1, newColor, visited);
        }
        image[sr][sc] = newColor;
    }

    public int[][] floodFillII(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if (color != newColor) dfs(image, sr, sc, color, newColor);
        return image;
    }
    public void dfs(int[][] image, int r, int c, int color, int newColor) {
        if (image[r][c] == color) {
            image[r][c] = newColor;
            if (r >= 1) dfs(image, r-1, c, color, newColor);
            if (c >= 1) dfs(image, r, c-1, color, newColor);
            if (r+1 < image.length) dfs(image, r+1, c, color, newColor);
            if (c+1 < image[0].length) dfs(image, r, c+1, color, newColor);
        }
    }

    public static void main(String[] args) {
        int[][] result = new FloodFill_733().floodFill(new int[][] {{ 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 }}, 1, 1, 2);

        for(int[] i: result) {
            for(int j: i) {
                System.out.println(j);
            }
        }
    }
}