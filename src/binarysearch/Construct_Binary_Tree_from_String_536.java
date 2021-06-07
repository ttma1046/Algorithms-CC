package binarysearch;

/*
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example 1:

Input: s = "4(2(3)(1))(6(5))"
Output: [4,2,6,3,1,5]

Example 2:

Input: s = "4(2(3)(1))(6(5)(7))"
Output: [4,2,6,3,1,5,7]

Example 3:

Input: s = "-4(2(3)(1))(6(5)(7))"
Output: [-4,2,6,3,1,5,7]

Constraints:

0 <= s.length <= 3 * 104
s consists of digits, '(', ')', and '-' only.
*/

/**
 * Definition for a binary tree node.
 */
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

class Construct_Binary_Tree_from_String_536 {
    /*
    public TreeNode str2tree(String s) {
        Stack<TreeNode> stack = new Stack<>();
        for(int i = 0, j = i; i < s.length(); i++, j = i) {
            char c = s.charAt(i);
            if(c == ')')    stack.pop();
            else if(c >= '0' && c <= '9' || c == '-') {
                while(i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
                TreeNode currentNode = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
                if(!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if(parent.left != null)    parent.right = currentNode;
                    else parent.left = currentNode;
                }
                stack.push(currentNode);
            }
        }
        return stack.isEmpty() ? null : stack.peek();
    }
    */

    public static void main(String[] args) {
        Construct_Binary_Tree_from_String_536 ob = new Construct_Binary_Tree_from_String_536();
        TreeNode res = ob.str2tree("4(2(3)(1))(6(5)(7))");
    }

    public TreeNode str2tree(String s) {
        int openBracket = s.indexOf("(");
        int rootVal = openBracket == -1 ? Integer.parseInt(s) : Integer.parseInt(s.substring(0, openBracket));
        TreeNode root = new TreeNode(rootVal);

        if (openBracket == -1) return root;

        int start = openBracket, closeBracketCount = 0;

        for (int i = start; i < s.length(); ++i) {
            if (s.charAt(i) == '(') closeBracketCount++;
            else if (s.charAt(i) == ')') closeBracketCount--;

            if (closeBracketCount == 0 && start == openBracket) { 
                cur.left = str2tree(s.substring(start + 1, i));
                start = i + 1;
            } else if (closeBracketCount == 0) { 
                cur.right = str2tree(s.substring(start + 1, i));
            }
        }
    }

    return root;
}































