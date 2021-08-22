/*
Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].

You may return the answer in any order.



Example 1:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
Example 2:

Input: n = 1, k = 1
Output: [[1]]


Constraints:

1 <= n <= 20
1 <= k <= n
*/


public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    void dfs(List<List<Integer>> res, List<Integer> level, int n, int k, int index) {
        if (level.size() == k) res.add(new ArrayList<>(level));
        else {

            for (int i = index; i <= n; i++) {
                level.add(i);
                dfs(res, level, n, k, i + 1);
                level.remove(level.size() - 1);
            }
        }
    }
}