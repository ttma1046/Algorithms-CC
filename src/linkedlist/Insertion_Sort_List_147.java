package linkedlist;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Insertion_Sort_List_147 {
    public ListNode sortList(ListNode head) {
    	ListNode dummy = new ListNode();

    	ListNode curr = head;

    	while (curr != null) {
    		List prev = dummy;

    		while (prev.next != null && prev.next.val < curr.val) {
    			prev = prev.next;
    		}

    		ListNode next = curr.next;

    		curr.next = prev.next;

    		prev.next = curr;

    		curr = next;
    	}

    	return dummy.next;

    }
}
/*
4 ----> 3 ----> 5

next = 3 ---> 5

4 ===> null

null => 4;

3--->5;
*/




