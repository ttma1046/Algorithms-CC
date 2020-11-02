package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
/*
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
*/

/*
Complexity Analysis

Time complexity : we visit each node exactly once, thus the time complexity is O(N), where NN is the number of nodes.

Space complexity : O(N).
Here we use the space for a stack call and for a paths list to store the answer.

paths contains as many elements as leafs in the tree and hence couldn't be larger than logN for the trees containing more than one element.

Hence the space complexity is determined by a stack call.

In the worst case, when the tree is completely unbalanced,

e.g. each node has only one child node, the recursion call would occur N times (the height of the tree),

therefore the storage to keep the call stack would be O(N).

But in the best case (the tree is balanced), the height of the tree would be log(N).

Therefore, the space complexity in this case would be \O(log(N)).

*/
class MyPair {
    TreeNode key;
    String value;

    MyPair(TreeNode y, String x) {
        key = y;
        value = x;
    }
}

class BinaryTreePaths_257 {
    /* Stack */
    public List<String> binaryTreePathsII(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if (root != null) {
            Stack<MyPair> stack = new Stack<MyPair>();

            stack.add(new MyPair(root, ""));

            while (!stack.isEmpty()) {
                MyPair item = stack.pop();

                TreeNode node = item.key;
                String path = item.value;

                path += Integer.toString(node.val);

                if (node.left == null && node.right == null) {
                    result.add(path);
                } else {
                    path += "->";

                    if (node.left != null) {
                        stack.add(new MyPair(node.left, path));
                    }

                    if (node.right != null) {
                        stack.add(new MyPair(node.right, path));
                    }
                }
            }
        }
        return result;
    }

    /* Two Stacks */
    public List<String> binaryTreePathsIII(TreeNode root) {
        List<String> result = new ArrayList<String>();

        if (root != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            Stack<String> pathStack = new Stack<String>();

            stack.add(root);
            pathStack.add("");

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                String path = pathStack.pop();

                path += Integer.toString(node.val);

                if (node.left == null && node.right == null) {
                    result.add(path);
                } else {
                    path += "->";

                    if (node.left != null) {
                        stack.add(node.left);
                        pathStack.add(path);
                    }

                    if (node.right != null) {
                        stack.add(node.right);
                        pathStack.add(path);
                    }
                }
            }
        }
        return result;
    }

    /* recursion */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();

        if (root != null) {
            StringBuilder sb = new StringBuilder();
            dfs(root, sb, result);
        }

        return result;
    }

    private void dfs(TreeNode node, StringBuilder path, List<String> result) {
        if (node != null) {
            path.append(Integer.toString(node.val));

            if (node.left == null && node.right == null) {
                result.add(path.toString());
            } else {
                path.append("->");
                String save = path.toString();
                dfs(node.left, path, result);
                dfs(node.right, new StringBuilder(save), result);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode five = new TreeNode(5);
        TreeNode two = new TreeNode(2);
        two.right = five;
        TreeNode three = new TreeNode(3);
        TreeNode one = new TreeNode(1);
        one.left = two;
        one.right = three;

        List<String> result = new BinaryTreePaths_257().binaryTreePathsII(one);

        for (String item : result) {
            System.out.println(item);
        }
    }
}