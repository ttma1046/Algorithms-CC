Intuition
This is similar to 1020. Number of Enclaves.

Approach 1: Flood Fill
First, we need to remove all land connected to the edges using flood fill.

Then, we can count and flood-fill the remaining islands.

image

Java

int fill(int[][] g, int i, int j) {
  if (i < 0 || j < 0 || i >= g.length || j >= g[i].length || g[i][j] != 0)
    return 0;
  return (g[i][j] = 1) + fill(g, i + 1, j) + fill(g, i, j + 1)
    + fill(g, i - 1, j) + fill(g, i, j - 1);
}
public int closedIsland(int[][] g) {
  for (int i = 0; i < g.length; ++i)
    for (int j = 0; j < g[i].length; ++j)
      if (i * j * (i - g.length + 1) * (j - g[i].length + 1) == 0)
        fill(g, i, j);
  int res = 0;
  for (int i = 0; i < g.length; ++i)
    for (int j = 0; j < g[i].length; ++j)
      res += fill(g, i, j) > 0 ? 1 : 0;
  return res;
}
C++

int fill(vector<vector<int>>& g, int i, int j) {
    if (i < 0 || j < 0 || i >= g.size() || j >= g[i].size() || g[i][j])
        return 0;
    return (g[i][j] = 1) + fill(g, i + 1, j) + fill(g, i, j + 1) 
        + fill(g, i - 1, j) + fill(g, i, j - 1);
}
int closedIsland(vector<vector<int>>& g, int res = 0) {
    for (int i = 0; i < g.size(); ++i)
        for (int j = 0; j < g[i].size(); ++j)
            if (i * j == 0 || i == g.size() - 1 || j == g[i].size() - 1)
                fill(g, i, j);
    for (int i = 0; i < g.size(); ++i)
        for (int j = 0; j < g[i].size(); ++j)
            res += fill(g, i, j) > 0;
    return res;
}
Complexity Analysis

Time: O(n), where n is the total number of cells. We flood-fill all land cells once.

Memory: O(n) for the stack. Flood fill is DFS, and the maximum depth is n.