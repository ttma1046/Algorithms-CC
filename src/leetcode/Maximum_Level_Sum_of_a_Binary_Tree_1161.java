package leetcode;

/*
Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the **smallest** level X such that the sum of all the values of nodes at level X is maximal.

Example 1:

Input: [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
 
Note:

The number of nodes in the given tree is between 1 and 10^4.
-10^5 <= node.val <= 10^5
*/
/*
Method 1: BFS

Use BFS to find the sum of each level, then locate the level with largest sum.
*/

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x) { val = x; }
}

public Maximum_Level_Sum_of_a_Binary_Tree_1161 {
    public int maxLevelSum(TreeNode root) {
        int max = Integer.MIN_VALUE, maxLevel = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        for (int level = 1; !q.isEmpty(); ++level) {
            int sum = 0;

            int sz = q.size();

            // for (int sz = q.size(); sz > 0; --sz) {
            while (size-- >= 0) {
                TreeNode n = q.poll();
                sum += n.val;
                if (n.left != null) {
                    q.offer(n.left);
                }
                if (n.right != null) {
                    q.offer(n.right);
                }
            }

            if (sum > max) {
                max = sum;
                maxLevel = level;
            }
        }

        return maxLevel;
    }
}
/*
Method 2: DFS
Use DFS to compute and store the sum of each level in an ArrayList, then locate the level with largest sum.

Recurse down from root, level of which is 0, increase level by 1 for each recursion down;
Use the level as the index of an ArrayList to store the sum of the correspoinding level;
Find the index of the max sum, then plus 1.
Java
*/

/*
public int maxLevelSum(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    dfs(root, list, 0);
    return 1 + IntStream.range(0, list.size()).reduce(0, (a, b) -> list.get(a) < list.get(b) ? b : a);
}
private void dfs(TreeNode n, List<Integer> l, int level) {
    if (n == null) { return; }
    if (l.size() == level) { l.add(n.val); } // never reach this level before, add first value.
    else { l.set(level, l.get(level) + n.val); } // reached the level before, accumulate current value to old value.
    dfs(n.left, l, level + 1);
    dfs(n.right, l, level + 1);
}
*/
// In case you are NOT comfortable with the Java 8 stream in the return statement, it can be written as:

/*
int maxLevel = 0;
for (int i = 0; i < list.size(); ++i) {
    if (list.get(maxLevel) < list.get(i)) {
        maxLevel = i;
    }
}
return maxLevel + 1;
*/