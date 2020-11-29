package tree;
import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
*/


// Definition for a binary tree node.
/*
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {}
	TreeNode(int val) { this.val = val; }
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
*/

public class Binary_Tree_Right_Side_View_199 {
	public List<Integer> rightSideView(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();

		List<Integer> res = new ArrayList<Integer>();

        if (root != null) {
            queue.offer(root);

            while (!queue.isEmpty()) {
                int length = queue.size();

                while (length > 0) {
                    TreeNode current = queue.poll();

                    if (current.left != null) queue.offer(current.left);
                    if (current.right != null) queue.offer(current.right);
                    if (length == 1) res.add(current.val);
                    length--;
                }
            }
        }

		return res;
	}

	public static void main(String[] args) {
		TreeNode five = new TreeNode(5);
		TreeNode two = new TreeNode(2);
		two.right = five;

		TreeNode four = new TreeNode(4);
		TreeNode three = new TreeNode(3);
		three.right = four;

		TreeNode root = new TreeNode(1);
		root.left = two;
		root.right = three;

		List<Integer> result = new Binary_Tree_Right_Side_View_199().rightSideView(root);

		for(int i: result) {
			System.out.println(i);
		}
	}
}