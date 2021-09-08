package tree;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree


as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.

Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by the null value.

For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].

You do not necessarily need to follow the above-suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.

Example 1:

Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]

Example 2:

Input: root = [1,null,3,2,4,null,5,6]
Output: [1,null,3,2,4,null,5,6]

Example 3:

Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 104].
0 <= Node.val <= 104
The height of the n-ary tree is less than or equal to 1000
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
*/

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

public class Serialization_and_Deserialize_N_ary_Tree_428 {
    public static void main(String[] args) {
        Codec obj = new Codec();

        Node five = new Node(5, new ArrayList<>());
        Node six = new Node(6, new ArrayList<>());

        List<Node> listTwo = new ArrayList<>();
        listTwo.add(five);
        listTwo.add(six);

        Node four = new Node(4, new ArrayList<>());
        Node two = new Node(2, new ArrayList<>());
        Node three = new Node(3, listTwo);

        List<Node> listOne = new ArrayList<>();
        listOne.add(three);
        listOne.add(two);
        listOne.add(four);

        Node one = new Node(1, listOne);

        String res = obj.serialize(one);

        System.out.println(res);

        letsprint(obj.deserialize(res));
    }

    private static void letsprint(Node node) {
        System.out.print(node.val);

        if (node.children != null) {
            System.out.println();

            for (Node k : node.children) {
                letsprint(k);
                System.out.print(" ");
            }
        }
    }
}

class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuffer sb = new StringBuffer();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(Node root, StringBuffer sb) {
        if(root == null) return;

        sb.append((char)(root.val + '0'));
        List<Node> children = root.children;
        int sz = children.size();
        sb.append((char)(sz + '0'));
        for(int i = 0 ; i < sz; i++) {
            preorder(children.get(i), sb);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        int[] index = new int[1];
        return constructTree(data, index);
    }

    private Node constructTree(String data, int[] index) {
        if(index[0] >= data.length()) {
            return null;
        }
        Node node = new Node(data.charAt(index[0]) - '0');
        node.children = new ArrayList<>();
        index[0]++;
        int childrenSz = data.charAt(index[0]) - '0';
        for(int i = 0 ; i < childrenSz; i++) {
            index[0]++;
            node.children.add(constructTree(data, index));
        }

        return node;
    }

    // Encodes a tree to a single string.
    public String serializeII(Node root) {
        List<String> list = new LinkedList<>();
        preorder(root, list);
        return String.join(",", list);
    }

    private void preorder(Node root, List<String> list) {
        if(root == null) {
            return;
        } else {
            list.add(String.valueOf(root.val));
            list.add(String.valueOf(root.children.size()));
            for(Node child : root.children) preorder(child, list);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserializeII(String data) {
        if(data.equals("")) return null;

        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return dfs(q);
    }

    private Node dfs(Queue<String> q) {
        Node root = new Node(Integer.parseInt(q.poll()), new ArrayList<>());
        int size = Integer.parseInt(q.poll());
        for(int i = 0; i < size; i++) root.children.add(dfs(q));
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));