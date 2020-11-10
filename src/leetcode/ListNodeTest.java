package leetcode;

class ListNode {
	int value;
	ListNode next;
	ListNode(int val) {
	   value = val;
	}
}


class ListNodeTest {
	public static void main(String[] args) {

		ListNode tail = new ListNode(3);

		ListNode second = new ListNode(2);

		ListNode head = new ListNode(0);


		second.next = tail;
		head.next = second;

		ListNode ans = new ListNode(1);
		ans.next = head;
		head.next = new ListNode(67);
		// head = head.next;
		// head = head.next;

		System.out.println(ans.next.next.value);
	}
}