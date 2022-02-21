package linkedlist;

/*
Given the head of a singly linked list and two integers left and right where left <= right,

reverse the nodes of the list from position left to position right, and return the reversed list.

Example 1:

Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
Example 2:

Input: head = [5], left = 1, right = 1
Output: [5]

Constraints:

The number of nodes in the list is n.
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n

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

/**/
class Reverse_Linked_List_II_92 {
    public ListNode reverseBetweenII(ListNode head, int left, int right) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;

        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        for (int i = 0; i < left - 1; i++) pre = pre.next;

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        // 1 - 2 - 3 - 4 - 5 ; m = 2; n = 4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        /*
        pre = 1
        start = 2
        then = 3

        2 -> 4
        3 -> 2
        1 -> 3
        */

        for (int i = 0; i < right - left; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;

            then = start.next;
        }

        /* 
        2 -> 5
        4 -> 3
        1 -> 4;

        then = 5;
        */ 

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;

        for (int i = 0; i < left - 1; i++) pre = pre.next;

        ListNode cur = pre.next;

        /*
        1 - 2 - 3 - 4 - 5 ; left = 2; right = 4

        pre = 1
        curr = 2,
        temp = 2

        1 -> 3
        2 -> 4

        1 -> 3 -> 2 -> 4

        temp = 3 -> 2
        1 -> 4
        2 -> 5

        1 -> 4 -> 3 -> 2 -> 5
        */

        for (int i = 0; i < right - left; i++) {
            ListNode temp = pre.next;
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre.next.next = temp;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode five = new ListNode(5), four = new ListNode(4), three = new ListNode(3), two = new ListNode(2), one = new ListNode(1);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        new Reverse_Linked_List_II_92().reverseBetween(one, 2, 4);
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

    public ListNode reverseBetween(ListNode head, int start, int end) {
    	ListNode fakeHead = new ListNode(-1);
    	fakeHead.next = head;
    	ListNode curr = fakeHead.next;

    	ListNode prev = fakeHead;

    	int i = 1;

    	while(i < start) {
    		prev = cur;
    		cur = cur.next;
    		i++;
    	}

    	ListNode node = prev;

    	while(i++ <= n) {
    		ListNode next = cur.next;
    		cur.next = prev;
    		prev = cur;
    		cur = next;
    	}

    	node.next.next= cur; // node.next是翻转后的尾巴 2 -> 5
    	node.next = prev;
    	return fakeHead.next;
    }
}