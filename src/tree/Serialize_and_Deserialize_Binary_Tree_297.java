package tree;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

/*
Serialization is the process of converting a data structure or object into a sequence of bits 
so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. 
You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Example 1:

Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]

Example 2:

Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 104].
-1000 <= Node.val <= 1000
*/
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

class Serialize_and_Deserialize_Binary_Tree_297 {
    public static void main(String[] args) {
        Codec test = new Codec();

        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        three.right = five;
        three.left = four;
        TreeNode one = new TreeNode(1);
        one.left = two;
        one.right = three;

        String res = test.serializeMy(one);
        System.out.println(res);

        TreeNode node = test.deserializeMy(res);
    }
}

class Codec {
    // Encodes a tree to a single string.
    public String serializeII(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        while(q.size() > 0) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (curr != null) {
                    sb.append(String.valueOf(curr.val));
                    q.offer(curr.left); 
                    q.offer(curr.right);
                } else {
                    sb.append("#");
                }
            }
        }

        return sb.toString();
    }

    public String serializeIII(TreeNode root) {
        if (root == null) return "#";

        return String.valueOf(root.val) + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeI(String data) {
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return helper(q);
    }

    private TreeNode helper(Queue<String> q) {
        String s = q.poll();
        System.out.println(s);

        if (s.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(s));
        root.left = helper(q);
        root.right = helper(q);
        return root;
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null)  {
            sb.append("#").append(",");
        } else {
            sb.append(root.val).append(",");

            serialize(root.left, sb);
            serialize(root.right, sb);
        }
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;

        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));

        return deserialize(queue);
    }

    private TreeNode deserialize(Queue<String> queue) {
        if (queue.size() == 0) return null;

        String curr = queue.poll();

        if (curr.equals("#")) return null;

        TreeNode node = new TreeNode(Integer.parseInt(curr));

        node.left = deserialize(queue);
        node.right = deserialize(queue);
        return node;
    }

    String serializeMy(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        serializeMy(root, sb);
        return sb.toString();
    }

    void serializeMy(TreeNode node, StringBuilder sb) {
        if (node == null) sb.append("#").append(",");
        else {
            sb.append(String.valueOf(node.val)).append(",");
            serializeMy(node.left, sb);
            serializeMy(node.right, sb);
        }
    }

    TreeNode deserializeMy(String data) {
        if (data.length() == 0 || data == null) return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeMy(q);
    }

    TreeNode deserializeMy(Queue<String> q) {
        if (q.size() == 0) return null;
        String curr = q.poll();
        if (curr.equals("#")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(curr));
        node.left = deserializeMy(q);
        node.right = deserializeMy(q);
        return node;
    }
}


// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
