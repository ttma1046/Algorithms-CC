package divideconquer;
import java.util.PriorityQueue;
/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []


Constraints:

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length won't exceed 10^4.
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

class Merge_k_sorted_lists_23 {
    public ListNode mergeKListsI(ListNode[] lists) {
        // [1,4,5],[1,3,4],[2,6]
        if (lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode list : lists) if (list != null) pq.offer(list);

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while(pq.size() > 0) {
            curr.next = pq.poll();

            curr = curr.next;

            if (pq.size() == 0) break;

            if (curr.next != null) pq.offer(curr.next);
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Merge_k_sorted_lists_23 obj = new Merge_k_sorted_lists_23();

        ListNode[] input = new ListNode[] { new ListNode(1) };
        obj.mergeKListsI(input);
    }

    // O(Nlogk)
    public ListNode mergeKListsII(ListNode[] lists) {
        return divide(lists, 0, lists.length - 1);
    }

    public ListNode divide(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];

        while(start < end) {
            int mid = start + (end - start) / 2;
            ListNode l1 = divide(lists, start, mid);
            ListNode l2 = divide(lists, mid + 1, end);

            return conquer(l1, l2);
        }
        return null;
    }

    public ListNode conquer(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = conquer(l1.next, l2);
            return l1;
        } else {
            l2.next = conquer(l1, l2.next);
            return l2;
        }
    }

    /*
    public ListNode conquer(ListNode l1, ListNode l2) {
        ListNode tempOne = l1;
        ListNode tempTwo = l2;

        ListNode result = new ListNode(-1);

        if (tempOne == null) return tempTwo;

        if (tempTwo == null) return tempOne;

        if (tempOne.val < tempTwo.val) {
            result = new ListNode(tempOne.val);
            tempOne = tempOne.next;
        } else {
            result = new ListNode(tempTwo.val);
            tempTwo = tempTwo.next;
        }

        ListNode temp = result;

        while (tempOne != null && tempTwo != null) {
            if (tempOne.val < tempTwo.val) {
                temp.next = new ListNode(tempOne.val);
                tempOne = tempOne.next;
            } else {
                temp.next = new ListNode(tempTwo.val);
                tempTwo = tempTwo.next;
            }

            temp = temp.next;
        }

        temp.next = tempOne == null ? tempTwo : tempOne;

        return result;
    }
    */
}