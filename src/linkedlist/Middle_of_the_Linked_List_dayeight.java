package linkedlist;

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }

public class Middle_of_the_Linked_List_dayeight {
    public ListNode middleNode(ListNode head) {
    	if (head == null) {
    		return null;
    	}

    	if (head.next == null) {
    		return head;
    	}


		ListNode slow = head;
		ListNode fast = head;

		while (fast.next != null && fast == null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
    }
}