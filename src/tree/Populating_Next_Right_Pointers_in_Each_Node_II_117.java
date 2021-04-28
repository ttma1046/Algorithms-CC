package tree;
import java.util.Queue;
import java.util.LinkedList;
/*
Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.



Follow up:

You may only use constant extra space.
Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.


Example 1:

Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.


Constraints:

The number of nodes in the given tree is less than 6000.
-100 <= node.val <= 100
*/

// Definition for a Node.
class NextNode {
    public int val;
    public NextNode left;
    public NextNode right;
    public NextNode next;

    public NextNode() {}

    public NextNode(int _val) {
        val = _val;
    }

    public NextNode(int _val, NextNode _left, NextNode _right, NextNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

class Populating_Next_Right_Pointers_in_Each_Node_II_117 {

    /*
    public NextNode connect(NextNode root) {
    	if (root == null) {
    		return root;
    	}

    	Queue<NextNode> q = new LinkedList<NextNode>();

    	q.offer(root);

    	while(!q.isEmpty()) {
    		int n = q.size();

    		for (int i = 0; i < n; i++) {
    			NextNode node = q.poll();

    			if (i < n - 1) {
    				node.next = q.peek();
    			}

    			if (node.left != null) q.offer(node.left);
    			if (node.right != null) q.offer(node.right);
    		}
    	}

    	return root;
    }
    */

    /*
    Complexity Analysis
    
    Time Complexity: O(N) since we process each node exactly once.

    Space Complexity: O(1) since we don't make use of any additional data structure 
    for traversing nodes on a particular level like the previous approach does.
    */
    public NextNode connectIII(NextNode root) {
        if (root == null) return root;
        
        NextNode leftMost = root;
        NextNode curr = null;
        NextNode prev = null;

        while(leftMost != null) {
            curr = leftMost;
            leftMost = null;
            prev = null;

            while (curr != null) {
                if (curr.left != null) {
                    if (prev != null)
                        prev.next = curr.left;
                    else
                        leftMost = curr.left;

                    prev = curr.left;
                }

                if (curr.right != null) {
                    if (prev != null)
                        prev.next = curr.right;
                    else
                        leftMost = curr.right;

                    prev = curr.right;
                }

                curr = curr.next;
            }
        }

        return root;
    }

    /*
    Complexity Analysis

    Time Complexity: O(N) since we process each node exactly once. 
    Note that processing a node in this context means popping the node from the queue and then establishing the next pointers.

    Space Complexity: O(N). 
    This is a perfect binary tree which means the last level contains N/2 nodes. 
    The space complexity for breadth first traversal 
    is the maximum space occupied and the space occupied by the queue is dependent upon the maximum number of nodes in particular level. 
    So, in this case, the space complexity would be O(N).
    */
    public NextNode connectII(NextNode root) {

        if (root == null) {
            return root;
        }

        // Initialize a queue data structure which contains
        // just the root of the tree
        Queue<NextNode> Q = new LinkedList<NextNode>();
        Q.add(root);

        // Outer while loop which iterates over
        // each level
        while (Q.size() > 0) {

            // Note the size of the queue
            int size = Q.size();

            // Iterate over all the nodes on the current level
            for(int i = 0; i < size; i++) {

                // Pop a node from the front of the queue
                NextNode node = Q.poll();

                // This check is important. We don't want to
                // establish any wrong connections. The queue will
                // contain nodes from 2 levels at most at any
                // point in time. This check ensures we only
                // don't establish next pointers beyond the end
                // of a level
                if (i < size - 1) {
                    node.next = Q.peek();
                }

                // Add the children, if any, to the back of
                // the queue
                if (node.left != null) {
                    Q.add(node.left);
                }
                if (node.right != null) {
                    Q.add(node.right);
                }
            }
        }

        // Since the tree has now been modified, return the root node
        return root;
    }

    /*
    public NextNode connect(NextNode root) {
        NextNode head = root; //The left most node in the lower level
        NextNode prev = null; //The previous node in the lower level
        NextNode curr = null; //The current node in the upper level

        while (head != null) {
            curr = head;
            prev = null;
            head = null;
            while (curr != null) {
                if (curr.left != null) {
                    if (prev != null)
                        prev.next = curr.left;
                    else
                        head = curr.left;
                    prev = curr.left;
                }
                if (curr.right != null) {
                    if (prev != null)
                        prev.next = curr.right;
                    else
                        head = curr.right;
                    prev = curr.right;
                }
                curr = curr.next;
            }
        }

        return root;
    }
    */

    public static void main(String[] args) {
        NextNode four = new NextNode(4);
        NextNode two = new NextNode(2);
        two.left = four;
        two.right = new NextNode(5);

        NextNode seven = new NextNode(7);
        NextNode three = new NextNode(3);
        three.right = seven;

        NextNode one = new NextNode(1);
        one.right = three;
        one.left = two;

        NextNode res = new Populating_Next_Right_Pointers_in_Each_Node_II_117().connectIII(one);

        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}