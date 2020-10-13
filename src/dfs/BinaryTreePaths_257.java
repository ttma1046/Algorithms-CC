package dfs;

import java.util.ArrayList;
import java.util.List;

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
class BinaryTreePaths_257 {
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

        List<String> result = new BinaryTreePaths_257().binaryTreePaths(one);

        for (String item : result) {
            System.out.println(item);
        }
    }
}