package tree;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

/*
Serialization is converting a data structure or object

into a sequence of bits so that it can be stored in a file or memory buffer,

or transmitted across a network connection link to be reconstructed

later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree.

There is no restriction on how your serialization/deserialization algorithm should work.

You need to ensure that a binary search tree can be serialized to a string,

and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Example 1:

Input: root = [2,1,3]
Output: [2,1,3]

Example 2:

Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 104].
0 <= Node.val <= 104
The input tree is guaranteed to be a binary search tree.
*/

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

public class Serialize_and_Deserialize_BST_449 {
    public static void main(String[] args) {
        Codec test = new Codec();

        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);

        TreeNode one = new TreeNode(1);
        one.left = two;
        one.right = three;

        String res = test.serialize(one);
        System.out.println(res);

        TreeNode node = test.deserialize(res);
    }
}

class Codec {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);

        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;

        sb.append(String.valueOf(root.val)).append(",");

        if (root.left != null) serialize(root.left, sb);
        if (root.right != null) serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;

        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));

        return deserialize(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode deserialize(Queue<String> q, int lower, int upper) {
        if (q.size() == 0) return null;

        String curr = q.peek();

        int value = Integer.parseInt(curr);

        if (value < lower ||  value > upper) return null;

        q.poll();

        TreeNode node = new TreeNode(value);

        node.left = deserialize(q, lower, value);
        node.right = deserialize(q, value, upper);

        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;