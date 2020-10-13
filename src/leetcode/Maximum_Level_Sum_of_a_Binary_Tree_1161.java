package leetcode;
import java.util.Queue;
import java.util.LinkedList;
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

public class Maximum_Level_Sum_of_a_Binary_Tree_1161 {
    public int maxLevelSumMy(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxLevel = 1;
        int maxSum = Integer.MIN_VALUE;

        Queue<TreeNode> myQueue = new LinkedList<TreeNode>();
        myQueue.offer(root);

        for (int level = maxLevel; !myQueue.isEmpty(); level++) {
            int sum = 0;
            int size = myQueue.size();
            while(size-- > 0) {
                TreeNode node = myQueue.poll();
                sum += node.val;
                if (node.left != null) {
                    myQueue.offer(node.left);
                }

                if (node.right != null) {
                    myQueue.offer(node.right);
                }
            }

            if (sum > maxSum) {
                maxSum = sum;
                maxLevel = level;
            }
        }
        return maxLevel;
    }
    
    
    public int maxLevelSum(TreeNode root) {
        int max = Integer.MIN_VALUE, maxLevel = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        for (int level = 1; !q.isEmpty(); ++level) {
            int sum = 0;

            for (int sz = q.size(); sz > 0; --sz) {
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
    
    public static void main(String[] args) {
        TreeNode four = new TreeNode(4);
        four.left = new TreeNode(7);
        TreeNode two = new TreeNode(2);
        two.left = four;
        two.right = new TreeNode(5);

        TreeNode six = new TreeNode(6);
        six.right = new TreeNode(8);
        TreeNode three = new TreeNode(3);
        three.right = six;

        TreeNode one = new TreeNode(1);
        one.right = three;
        one.left = two;
        System.out.println(new Maximum_Level_Sum_of_a_Binary_Tree_1161().maxLevelSum(one));
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