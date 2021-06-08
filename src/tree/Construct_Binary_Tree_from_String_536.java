package tree;
import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;
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


    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) return null;
        Deque<TreeNode> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            int number = getNumber(s, i);
            TreeNode curr = new TreeNode(number);

            stack.push(curr);


            if (s.charAt(i) == '(')


            if (s.charAt(i) == ')') {
                TreeNode abc = stack.pop();

                if (!stack.isEmpty() && stack.peek().left == null) stack.peek().left = abc;
                else if (!stack.isEmpty()) stack.peek().right = abc;
            }
        }

        return stack.pop();
    }

    public int getNumber(String s, int index) {
        boolean negativeFlag = false;
        int res = 0;
        if (s.charAt(index) == '-') { 
            negativeFlag = true;
            index++;
        } 

        while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
            res = res * 10 + (s.charAt(index) - '0');
            index++;
        }

        return negativeFlag ? -number : number;
    }


    public Pair<Integer, Integer> getNumber(String s, int index) {
        boolean isNegative = false;

        // A negative number
        if (s.charAt(index) == '-') {
            isNegative = true;
            index++;
        }

        int number = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            number = number * 10 + (s.charAt(index) - '0');
            index++;
        }

        return new Pair<Integer, Integer>(isNegative ? -number : number, index);
    }





    public TreeNode str2tree(String s) {
        Stack<TreeNode> stack = new Stack<>();
        for(int i = 0, j = i; i < s.length(); i++, j = i) {
            char c = s.charAt(i);
            if(c == ')') stack.pop();
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

    public TreeNode str2treeII(String s) {
        if (s == null || s.length() == 0) return null;

        Deque<TreeNode> stack = new ArrayDeque<>();

        int index = 0;

        StringBuilder sb = new StringBuilder();

        while (index < s.length()) {
            if (s.charAt(index) == '(') {
                sb = new StringBuilder();
                index++;
            } else if (s.charAt(index) == ')') {
                TreeNode cur = stack.pop();
                if (!stack.isEmpty() && stack.peek().left == null) stack.peek().left = cur;
                else if (!stack.isEmpty()) stack.peek().right = cur;
                index++;
            } else {
                while(index < s.length() && s.charAt(index) != ')' && s.charAt(index) != '(') sb.append(s.charAt(index++));
                stack.push(new TreeNode(Integer.valueOf(sb.toString())));
            }
        }
        
        return stack.peek();
    }

    public static void main(String[] args) {
        Construct_Binary_Tree_from_String_536 ob = new Construct_Binary_Tree_from_String_536();
        TreeNode res = ob.str2tree("4(2(3)(1))(6(5)(7))");
    }

    public TreeNode str2treeIII(String s) {
        if (s == null || s.length() == 0) return null;

        int openBracket = s.indexOf("(");

        String nodeStr = openBracket > -1 ? s.substring(0, openBracket) : s;

        TreeNode node = new TreeNode(Integer.parseInt(nodeStr));

        if (openBracket == -1) return node;

        int start = openBracket, closeBracketCount = 0;

        for (int i = start; i < s.length(); ++i) {
            if (s.charAt(i) == '(') closeBracketCount++;
            else if (s.charAt(i) == ')') closeBracketCount--;

            if (closeBracketCount == 0 && start == openBracket) {
                node.left = str2tree(s.substring(start + 1, i));
                start = i + 1;
            } else if (closeBracketCount == 0) node.right = str2tree(s.substring(start + 1, i));
        }

        return node;
    }

    /*
    int i = 0, n;

    public TreeNode str2treeIV(String s) {
        n = s.length();
        return helper(s);
    }

    public TreeNode helper(String s) {
        if (i >= n) return null;
        if (s.charAt(i) == ')') {
            i++;
            return null;
        }

        int val = 0, sign = 1;
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        val = s.charAt(i++) - '0';
        while (i < n && s.charAt(i) != '(' && s.charAt(i) != ')')
            val = (val * 10) + (s.charAt(i++) - '0');
        TreeNode root = new TreeNode(val * sign);

        if (i < n && s.charAt(i) == '(') {
            i++;
            root.left = helper(s);
        }
        if (i < n && s.charAt(i) == '(') {
            i++;
            root.right = helper(s);
        }

        i++;

        return root;
    }

    public TreeNode str2tree(String s) {
        return str2tree2(s);
    }

    int index = 0;
    public TreeNode str2tree2(String s) {
        if(s.length() == 0) {
            return null;
        }
        int val = 0;
        int sign = 1;
        while(index < s.length() && s.charAt(index) != '(' && s.charAt(index) != ')') {
            if(s.charAt(index) == '-') {
                sign = -1;
            } else {
                val = val * 10 + (s.charAt(index) - '0');
            }
            index++;
        }
        TreeNode root = new TreeNode(val * sign);
        if(index < s.length() && s.charAt(index) == '(') {
            index++;
            root.left = str2tree2(s);
        }
        if(index < s.length() && s.charAt(index) == '(') {
            index++;
            root.right = str2tree2(s);
        }
        index++;
        return root;
    }

    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) return null;

        Stack<TreeNode> stack = new Stack<>();
        int index = 0;

        while (index < s.length()) {
            char c = s.charAt(index);

            if (c == ')') {
                stack.pop();
            } else if (c != '(') {
                index = getNode(s, index, stack);
                continue;
            }

            index++;
        }

        return stack.isEmpty() ? null : stack.pop();
    }

    private int getNode(String s, int index, Stack<TreeNode> stack) {
        int sign = 1;

        if (s.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        int num = 0;

        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            num = num * 10 + (s.charAt(index) - '0');
            index++;
        }

        var cur = new TreeNode(sign * num);
        if (!stack.isEmpty()) {
            var parent =  stack.peek();
            if (parent.left == null) {
                parent.left = cur;
            } else {
                parent.right = cur;
            }
        }

        stack.push(cur);

        return index;
    }

    private int index;

    public TreeNode str2tree(String s) {
        return buildTree(s);
    }

    private TreeNode buildTree(String s) {
        // base cases
        if (index >= s.length()) {
            return null;
        }

        // if close bracket ')', then return to caller.
        // Increment index to process next character
        if (s.charAt(index) == ')') {
            index++;
            return null;
        }

        int val = getValue(s);
        TreeNode root = new TreeNode(val);

        // check for end of string
        // if open bracket '(', then add left child
        if (index < s.length() && s.charAt(index) == '(') {
            index++;
            root.left = buildTree(s);
        }

        // check for end of string
        // if open bracket '(', then add right child
        if (index < s.length() && s.charAt(index) == '(') {
            index++;
            root.right = buildTree(s);
        }

        // increase the index to get pass the closed bracket in
        // the current subtree
        index++;

        // return the node created
        return root;
    }

    private int getValue(String s) {
        int start = index;
        int sign = 1;
        if (s.charAt(index) == '-') {
            sign = -1;
            index++;
        }
        int sum = 0;
        while (index < s.length() && s.charAt(index) - '0' >= 0 && s.charAt(index) - '0' <= 9) {
            sum = s.charAt(index) - '0' + 10 * sum;
            index++;
        }
        return sum * sign;
    }

    public TreeNode str2tree(String s) {
        Pair<TreeNode, Integer> res = this.str2treeInternal(s, 0);

        return res.getKey();
    }

    public Pair<TreeNode, Integer> str2treeInternal(String s, int index) {
        if (index == s.length()) {
            return new Pair<TreeNode, Integer>(null, index);
        }

        // Start of the tree will always contain a number representing
        // the root of the tree. So we calculate that first.
        Pair<Integer, Integer> numberData = this.getNumber(s, index);
        int value = numberData.getKey();
        index = numberData.getValue();

        TreeNode node = new TreeNode(value);
        Pair<TreeNode, Integer> data;

        // Next, if there is any data left, we check for the first subtree
        // which according to the problem statement will always be the left child.
        if (index < s.length() && s.charAt(index) == '(') {
            data = this.str2treeInternal(s, index + 1);
            node.left = data.getKey();
            index = data.getValue();
        }

        // Indicates a right child
        if (node.left != null && index < s.length() && s.charAt(index) == '(') {
            data = this.str2treeInternal(s, index + 1);
            node.right = data.getKey();
            index = data.getValue();
        }

        return new Pair<TreeNode, Integer>(node, index < s.length() && s.charAt(index) == ')' ? index + 1 : index);
    }

    public Pair<Integer, Integer> getNumber(String s, int index) {
        boolean isNegative = false;

        // A negative number
        if (s.charAt(index) == '-') {
            isNegative = true;
            index++;
        }

        int number = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            number = number * 10 + (s.charAt(index) - '0');
            index++;
        }

        return new Pair<Integer, Integer>(isNegative ? -number : number, index);
    }

    public TreeNode str2tree(String s) {
        if (s.isEmpty()) return null;

        TreeNode root = new TreeNode();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);

        for (int index = 0; index < s.length(); ++index) {
            TreeNode node = stack.pop();

            // NOT_STARTED
            if (Character.isDigit(s.charAt(index)) || s.charAt(index) == '-') {
                Pair<Integer, Integer> numberData = this.getNumber(s, index);
                int value = numberData.getKey();
                index = numberData.getValue();

                node.val = value;

                // Next, if there is any data left, we check for the first subtree
                // which according to the problem statement will always be the left child.
                if (index < s.length() && s.charAt(index) == '(') {
                    stack.add(node);

                    node.left = new TreeNode();
                    stack.add(node.left);
                }
            } else if (s.charAt(index) == '(' && node.left != null) { // LEFT_DONE
                stack.add(node);

                node.right = new TreeNode();
                stack.add(node.right);
            }
        }

        return stack.empty() ? root : stack.pop();
    }

    public Pair<Integer, Integer> getNumber(String s, int index) {
        boolean isNegative = false;

        // A negative number
        if (s.charAt(index) == '-') {
            isNegative = true;
            index++;
        }

        int number = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            number = number * 10 + (s.charAt(index) - '0');
            index++;
        }

        return new Pair<Integer, Integer>(isNegative ? -number : number, index);
    }



    public Pair<Integer, Integer> getNumber(String s, int index) {
        
    }
    */
}





























