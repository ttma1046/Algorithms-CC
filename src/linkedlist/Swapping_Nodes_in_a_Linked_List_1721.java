package linkedlist;

class Swapping_Nodes_in_a_Linked_List_1721 {
	public ListNode swapNodes(ListNode head, int k) {
		int listLength = 0;
		ListNode frontNode = null;
		ListNode endNode = null;
		ListNode currentNode = head;
		// find the length of list and set the front node
		while (currentNode != null) {
			listLength++;
			if (listLength == k) {
				frontNode = currentNode;
			}
			currentNode = currentNode.next;
		}
		// set the end node at (listLength - k)th node
		endNode = head;
		for (int i = 1; i <= listLength - k; i++) {
			endNode = endNode.next;
		}
		// swap front node and end node values
		int temp = frontNode.val;
		frontNode.val = endNode.val;
		endNode.val = temp;
		return head;
	}

	public ListNode swapNodes(ListNode head, int k) {
		int listLength = 0;

		ListNode currentNode = head;
		// find the length of linked list
		while (currentNode != null) {
			listLength++;
			currentNode = currentNode.next;
		}

		// set the front node at kth node
		ListNode frontNode = head;
		for (int i = 1; i < k; i++) {
			frontNode = frontNode.next;
		}

		//set the end node at (listLength - k)th node
		ListNode endNode = head;
		for (int i = 1; i <= listLength - k; i++) {
			endNode = endNode.next;
		}

		// swap the values of front node and end node
		int temp = frontNode.val;
		frontNode.val = endNode.val;
		endNode.val = temp;
		return head;
	}


	public ListNode swapNodes(ListNode head, int k) {
		int listLength = 0;

		ListNode currentNode = head;
		ListNode frontNode = null;
		ListNode endNode = null;

		while(currentNode != null) {
			listLength++;

			if (listLength == k) {
				frontNode = currentNode;
				endNode = head;
			}

			if (endNode != null) {
				endNode = endNode.next;
			}

			currentNode = currentNode.next;
		}

		// swap the values of front node and end node
		int temp = frontNode.val;
		frontNode.val = endNode.val;
		endNode.val = temp;
		return head;
	}



	public ListNode swapNodes(ListNode head, int k) {
		int listLength = 0;

		ListNode currentNode = head;
		ListNode frontNode = null;
		ListNode endNode = null;

		while(currentNode != null) {
			listLength++;

			if (endNode != null) {
				endNode = endNode.next;
			}

			if (listLength == k) {
				frontNode = currentNode;
				endNode = head;
			}

			currentNode = currentNode.next;
		}

		// swap the values of front node and end node
		int temp = frontNode.val;
		frontNode.val = endNode.val;
		endNode.val = temp;
		return head;
	}


	public ListNode swapNodes(ListNode head, int k) {


		int listLength = 0;

		ListNode currentNode = head;
		ListNode frontNode = null;
		ListNode endNode = null;

		while(currentNode != null) {
			listLength++;

			if (endNode != null) endNode = endNode.next;

			if (listLength == k) {
				frontNode = currentNode;
				endNode = head;
			}

			currentNode = currentNode.next;
		}

		int temp = frontNode.val;
		frontNode.val = endNode.val;
		endNode.val = temp;
		return head;
	}
}