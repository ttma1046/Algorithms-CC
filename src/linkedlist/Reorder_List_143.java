package linkedlist;

/*
You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

Example 1:

Input: head = [1,2,3,4]
Output: [1,4,2,3]

Example 2:

Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]

Constraints:

The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
*/
class Reorder_List_143 {
  public void reorderList(ListNode head) {
    if (head == null) return;

    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    ListNode prev = null, curr = slow, tmp = null;
    while (curr != null) {
      tmp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = tmp;
    }

    // 1->2->3->4 6->5->4
    ListNode list1 = head, list2 = prev;
    while(list2.next != null) {
      tmp = list1.next;
      list1.next = list2;
      list1 = tmp;

      tmp = list2.next;
      list2.next = list1;
      list2 = tmp;
    }
  }

  public void reorderListII(ListNode head) {
  	if(head == null) return;

  	ListNode slow = head, fast = head;

  	while (fast != null && fast.next != null) {
  		fast = fast.next.next;
  		slow = slow.next;
  	}

  	ListNode prev = null, curr = slow, tmp;
  	while(curr != null) {
  		tmp = curr.next;

  		curr.next = prev;
  		prev = curr;
  		curr = tmp;
  	}

  	ListNode first = node, second = slow;

  	While() {
  		ListNode tmp = first.next;

  		first.next = second;

  		first = tmp;

  		tmp = second.next;
  		second.next = first;
  		second = tmp;	
  	}
  }

  public static void main(String[] args) {
    Reorder_List_143 obj = new Reorder_List_143();
  }
}