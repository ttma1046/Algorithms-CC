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
class Remove_Duplicates_from_Sorted_List_II {
	public ListNode deleteDuplicatesII(ListNode head) {
		if (head == null) return null;
		ListNode node = new ListNode(0);
		node.next = head;
		ListNode pre = node;
		ListNode fast = head;

		while (fast != null) {
			while (fast.next != null && fast.val == fast.next.val) {
				fast = fast.next;
			}

			if (pre.next == fast) {
				pre = pre.next;
				System.out.println("did");
			} else {
				pre.next = fast.next;
				System.out.println(pre.next.val);
			}

			fast = fast.next;
			System.out.println(pre.next.val);

		}

		System.out.println(node.val);
		System.out.println(node.next.val);

		return node.next;
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) return null;

		ListNode node = new ListNode(0);

		node.next = head;

		ListNode pre = node;


		ListNode temp = node;

		ListNode fast = head;


		while (fast != null) {
			while (fast.next != null && fast.val == fast.next.val) {
				fast = fast.next;
			}

			if (pre.next == fast) {
				pre = pre.next;
				System.out.println("pre.val:" + pre.val);
			} else {
				pre.next = fast.next;
				System.out.println("prenextval:" + pre.next.val);
				System.out.println("preval:" + pre.val);
			}

			fast = fast.next;
			// System.out.println("prenextval:" + pre.next.val);
			// System.out.println("preval:" + pre.val);

			while (temp != null) {
				System.out.println(temp.val);
				temp = temp.next;

			}
			temp = node;
		}

		return node.next;
	}

	public static void main(String[] args) {
		/*		
		ListNode three = new ListNode(3);

		ListNode two = new ListNode(2);
		two.next = three;

		ListNode one = new ListNode(1);
		one.next = two;

		ListNode pre = new ListNode(1);
		pre.next = one;

		ListNode prepre = new ListNode(1);

		prepre.next = pre;

		ListNode result = new Remove_Duplicates_from_Sorted_List_II().deleteDuplicates(prepre);
		*/

		ListNode five = new ListNode(5);

		ListNode fourTwo = new ListNode(4);
		fourTwo.next = five;

		ListNode fourOne = new ListNode(4);
		fourOne.next = fourTwo;

		ListNode threeOne = new ListNode(3);
		threeOne.next = fourOne;

		ListNode three = new ListNode(3);
		three.next = threeOne;

		ListNode two = new ListNode(2);
		two.next = three;

		ListNode one = new ListNode(1);
		one.next = two;

		ListNode result = new Remove_Duplicates_from_Sorted_List_II().deleteDuplicates(one);

	}
}