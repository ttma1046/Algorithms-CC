package tree;

import java.util.Stack;


public class ConstructBinarySearchTreefromPreOrderTraversal_1008 {
	private int index;

	public TreeNode bstFromPreorder(int[] preorder) {
		if (preorder == null || preorder.length <= 0) {
			return null;
		}

		return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, preorder);
	}

	private TreeNode helper(int minLimit, int maxLimit, int[] array) {
		if (array.length == index) {
			return null;
		}

		int value = array[index];
		if (value <= minLimit || value >= maxLimit) {
			return null;
		}

		index++;

		TreeNode node = new TreeNode(value);
		node.left = helper(minLimit, value, preorder);
		node.right = helper(value, maxLimit, preorder);

		return node;
	}

	public static void main(String[] args) {
        TreeNode result = new ConstructBinarySearchTreefromPreOrderTraversal_1008().bstFromPreorder(new int[] { 8, 5, 1, 7, 10, 12 });

        printPreOrder(result);
    }

    private static void printPreOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        printPreOrder(node.left);
        printPreOrder(node.right);
    }


    /*
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        int n = preorder.length;
        TreeNode node = new TreeNode(preorder[0]);
        Stack<TreeNode> myStack = new Stack<TreeNode>();
        myStack.push(node);

        for(int i = 1; i < n; i++) {
            TreeNode temp = myStack.peek();
            TreeNode child = new TreeNode(preorder[i]);

            while(!myStack.isEmpty() && myStack.peek().val < child.val) {
                temp = myStack.pop();
            }

            if (child.val > temp.val) {
                temp.right = child;
            } else {
                temp.left = child;
            }

            myStack.push(child);
        }

        return node;
    }
    */
    /*
    int idx = 0;
    int[] preorder;
    int n;

    public TreeNode helper(int lower, int upper) {
        // if all elements from preorder are used
        // then the tree is constructed
        if (idx == n) return null;

        int val = preorder[idx];
        // if the current element
        // couldn't be placed here to meet BST requirements
        if (val < lower || val > upper) return null;

        // place the current element
        // and recursively construct subtrees
        idx++;
        TreeNode root = new TreeNode(val);
        root.left = helper(lower, val);
        root.right = helper(val, upper);
        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        this.preorder = preorder;
        n = preorder.length;
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    */
    int index = 0;
    private TreeNode helper(int lower, int upper, int[] array) {
    	if (index == array.length) {
    		return null;
    	}

    	int val = array[index];
    	if (val < lower || val > upper) return null;
    	index++;
    	TreeNode node = new TreeNode(val);
        node.left = helper(lower, val, array);
        node.right = helper(val, upper, array);
        return node;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
    	if (preorder == null || preorder.length <= 0) {
    		return null;
    	}

    	return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, preorder);
   	}

    public static void main(String[] args) {
        TreeNode result = new ConstructBinarySearchTreefromPreOrderTraversal_1008().bstFromPreorder(new int[] { 8, 5, 1, 7, 10, 12 });

        printPreOrder(result);
    }

    private static void printPreOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        printPreOrder(node.left);
        printPreOrder(node.right);
    }
}