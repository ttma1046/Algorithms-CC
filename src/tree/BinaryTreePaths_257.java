package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class BinaryTreePaths_257 {

    /*
    public List<String> binaryTreePaths(TreeNode root) {
        TreeNode curr = root;

        List<String> res = new ArrayList<>();

        while(curr != null) {
            if (curr.left == null && curr.right == null) {
                res.add(temp);
            } else {
                String temp += "->";

                if (curr.left != null) {
                    temp += String.valueOf(curr.left);

                    curr = curr.left;
                }

                if (curr.right != null) {
                    temp += String.valueOf(curr.left);

                    curr = curr.right;
                }
            }
        }

        return res;
    }
    */


    /* BFS - Queue */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<String> path = new LinkedList<>();

        path.offer(root.val + "");
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            String item = path.poll();

            if (cur.left == null && cur.right == null) res.add(item);

            if (cur.left != null) {
                queue.offer(cur.left);
                path.offer(item + "->" + cur.left.val);
            }

            if (cur.right != null) {
                queue.offer(cur.right);
                path.offer(item + "->" + cur.right.val);
            }
        }

        return res;
    }

    /* Stack */
    public List<String> binaryTreePathsII(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if (root != null) {
            Stack<MyPair> stack = new Stack<MyPair>();

            stack.add(new MyPair(root, ""));

            while (stack.size() > 0) {
                System.out.println(stack.size());

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

    // dfs StringBuilder II
    public List<String> binaryTreePathsSBII(TreeNode root) {
        List<String> result = new ArrayList<String>();

        if (root != null) {
            StringBuilder sb = new StringBuilder();
            dfsII(root, sb, result);
        }

        return result;
    }

    private void dfsII(TreeNode node, StringBuilder path, List<String> result) {
        if (node != null) {
            path.append(Integer.toString(node.val));

            if (node.left == null && node.right == null) {
                result.add(path.toString());
            } else {
                path.append("->");
                String save = path.toString();
                dfsII(node.left, path, result);
                dfsII(node.right, new StringBuilder(save), result);
            }
        }
    }

    // dfs StringBuilder
    public List<String> binaryTreePathsSB(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(root.val));
            dfsSB(root, sb, res);
        }
        return res;
    }

    private void dfsSB(TreeNode root, StringBuilder sb, List<String> res) {
        if (root.left == null && root.right == null) res.add(sb.toString());

        String save = sb.toString();

        if (root.left != null) {
            sb.append("->");
            dfsSB(root.left, sb.append(root.left.val), res);
        }

        if (root.right != null) {
            StringBuilder anotherSB = new StringBuilder(save);

            anotherSB.append("->");
            dfsSB(root.left, sb.append(root.right.val), res);
        }
    }

    // dfs
    public List<String> binaryTreePathsSlow(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root != null) helper(root, String.valueOf(root.val), res);
        return res;
    }

    private void helper(TreeNode node, String path, List<String> res) {
        if (node.left == null && node.right == null) {
            res.add(path);
            return;
        }

        if (node.left != null) helper(node.left, path + "->" + node.left.val, res);
        if (node.right != null) helper(node.right, path + "->" + node.right.val, res);
    }

    public List<String> binaryTreePathsIV(TreeNode root) {
        List<String> result = new ArrayList<String>();

        if (root != null) myDfs(root, "", result);

        return result;
    }

    private void myDfs(TreeNode current, String currentString, List<String> result) {
        if (current == null) return;

        currentString += Integer.toString(current.val);

        if (current.right == null && current.left == null) {
            result.add(currentString);
            return;
        }

        currentString += "->";

        myDfs(current.left, currentString, result);
        myDfs(current.right, currentString, result);
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
    }



    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) 
            return result;

        List<TreeNode> current = new ArrayList<>();
        backTracking(root, result, current, 0);
        return result;
    }

    private void backTracking(TreeNode root, List<String> result, List<TreeNode> current, int level) {
        if (root == null) 
            return;
        
        current.add(root); // 进backTracking先吃，root一定是一个解 -> 对应退出这个函数的吐

        // 什么是一个解？root到leaf就是一个解。左右都没孩子就是leaf
        // 哪里收集解？leaf 收集解-> 加入result
        if (root.left == null && root.right == null) {
            result.add(stringify(current));
            current.remove(current.size() - 1); // return到上一层 有吃必有吐
            return; // return
        }

        // 每层是什么？加一个node。
        // 分支是什么？左和右
        if (root.left != null)
            backTracking(root.left, result, current, level + 1);

        if (root.right != null)
            backTracking(root.right, result, current, level + 1);

        current.remove(current.size() - 1); // return到上一层有吃必有吐-> 对应刚进函数的吃
    }

    private String stringify(List<TreeNode> current) {
        StringBuilder sb = new StringBuilder();
        for (TreeNode node : current) {
            sb.append(node.val);
            sb.append("->");
        }
        return sb.substring(0, sb.length() - 2);
    }
}