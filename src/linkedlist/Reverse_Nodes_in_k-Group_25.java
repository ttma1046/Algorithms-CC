package linkedlist;

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
/**/
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
    	int length = countLength(head);
    	int i = 1;
    	while (i + k <= length + 1) {
    		head = reverseBetween(head, i, i + k - 1);
    		i += k;
    	}

    	return head;
    }

    public ListNode reverseBetweenIterative(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode curr1 = dummy;
        ListNode prev1 = null;

        for (int i = 0; i < left; i++) {
            prev1 = curr1;
            curr1 = curr1.next;
        }

        ListNode curr2 = curr1;
        ListNode prev2 = prev1;

        for(int i = left; i <= right; i++) {
            ListNode q2 = curr2.next;
            curr2.next = prev2;

            prev2 = curr2;

            curr2 = q2;
        }

        prev1.next = prev2;
        curr1.next = curr2;

        return dummy.next;
    }

    private int countLength(ListNode cur) {
    	int count = 0;
    	while(cur != null) {
    		cur = cur.next;
    		count++;
    	}
    	return count;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
    	ListNode node = head;
    	int count = 0;
    	while(count < k) {
    		if (node == null) return head;
    		node = node.next;
    		count++;
    	}

    	ListNode pre = reverseKGroup(node, k);
    	while(count-- > 0) {
    		ListNode next = head.next;
    		head.next = pre;
    		pre = head;
    		head = next;
    	}

    	return pre;
    }
}