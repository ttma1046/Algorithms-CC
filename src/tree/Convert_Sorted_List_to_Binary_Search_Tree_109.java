package tree;
import java.util.List;
import java.util.ArrayList;
/*
Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:

Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.

Example 2:

Input: head = []
Output: []

Constraints:

The number of nodes in head is in the range [0, 2 * 104].
-105 <= Node.val <= 105
*/

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

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

class Convert_Sorted_List_to_Binary_Search_Tree_109 {
    ListNode findMiddle(ListNode node) {
        if (node == null) return node;

        ListNode prev = null;
        ListNode slow = node;
        ListNode fast = node;

        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (prev != null)
            prev.next = null;

        return slow;
    }

    TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;

        ListNode mid = findMiddle(head);

        TreeNode node = new TreeNode(mid.val);

        if (mid == head)
            return node;

        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);
        return node;
    }

    public static void main(String[] args) {
        Convert_Sorted_List_to_Binary_Search_Tree_109 obj
            = new Convert_Sorted_List_to_Binary_Search_Tree_109();
    }

    /*
    Time Complexity: O(NlogN).

    Suppose our linked list consists of N elements.

    For every list we pass to our recursive function, we have to calculate the middle element for that list.

    For a list of size N, it takes N / 2 steps to find the middle element

    i.e. O(N) to find the mid.

    We do this for every half of the original linked list. From the looks of it, this seems to be an O(N^2) algorithm.

    However, on closer analysis, it turns out to be a bit more efficient than O(N^2).

    Let's look at the number of operations that we have to perform on each of the halves of the linked list.

    As we mentioned earlier, it takes N/2 steps to find the middle of a linked list with N elements.

    After finding the middle element, we are left with two halves of size N / 2 each.

    Then, we find the middle element for both of these halves and it would take a total of 2 × N / 4 steps for that.

    And similarly for the smaller sublists that keep forming recursively. This would give us the following series of operations for a list of size N.

    (N / 2) + 2 * (N / 4) + 4 * (N / 8) + 8 * (N / 16)

    Essentially, this is done log N times since we split the linked list in half every time.

    Hence, the above equation becomes:

    O (N log N)

    Space Complexity: O(log N).

    Since we are resorting to recursion,

    there is always the added space complexity of the recursion stack that comes into picture.

    This could have been O(N) for a skewed tree, but the question clearly states that we need to maintain the height balanced property.

    This ensures the height of the tree to be bounded by O(logN). Hence, the space complexity is O(logN).
    */

    public List<Integer> list = new ArrayList<Integer>();

    public TreeNode sortedListToBSTII(ListNode head) {
        if (head == null) return null;

        convertLinkedListtoList(head);

        return convertListToTree(0, this.list.size() - 1);
    }

    private void convertLinkedListtoList(ListNode head) {
        while(head != null) {
            this.list.add(head.val);
            head = head.next;
        }
    }

    private TreeNode convertListToTree(int left, int right) {
        if (left > right)
            return null;

        int mid = left + (left - right) / 2;
        TreeNode node = new TreeNode(this.list.get(mid));

        if (left == right)
            return node;

        node.left = convertListToTree(left, mid - 1);
        node.right = convertListToTree(mid + 1, right);

        return node;
    }

    /*
    Complexity Analysis

    Time Complexity: The time complexity comes down to just O(N) now since we convert the linked list to an array initially and then we convert the array into a BST.
    Accessing the middle element now takes O(1) time and hence the time complexity comes down.

    Space Complexity: Since we used extra space to bring down the time complexity, the space complexity now goes up to O(N) as opposed to just O(logN) in the previous solution.
    This is due to the array we construct initially.
    */

    private ListNode head;

    public TreeNode sortedListToBSTIII(ListNode head) {
        // Get the size of the linked list first
        int size = this.findSize(head);

        this.head = head;

        // Form the BST now that we know the size
        return convertListToBST(0, size - 1);
    }

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;

        while (ptr != null) {
            ptr = ptr.next;
            c++;
        }

        return c;
    }

    private TreeNode convertListToBST(int left, int right) {
        // Invalid case
        if (left > right)
            return null;

        int mid = (left + right) / 2;

        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode leftNode = this.convertListToBST(left, mid - 1);

        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(this.head.val);

        if (leftNode != null)
            node.left = leftNode;

        // Maintain the invariance mentioned in the algorithm
        this.head = this.head.next;

        // Recurse on the right hand side and form BST out of them
        TreeNode rightNode = this.convertListToBST(mid + 1, right);
        if (leftNode != null)
            node.right = rightNode;

        return node;
    }

    /*
    Complexity Analysis

    Time Complexity:
    The time complexity is still O(N) since we still have to process each of the nodes in the linked list once
    and form corresponding BST nodes.

    Space Complexity: O(logN) since now the only extra space is used by the recursion stack
    and since we are building a height balanced BST, the height is bounded by logN.
    */
}
