package linkedlist;
/*
Given the head of a singly linked list,

sort the list using insertion sort,

and return the sorted list's head.

The steps of the insertion sort algorithm:

1. Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
2. At each iteration, insertion sort removes one element from the input data,
finds the location it belongs within the sorted list and inserts it there.
3. It repeats until no input elements remain.

The following is a graphical example of the insertion sort algorithm.
The partially sorted list (black) initially contains only the first element in the list.
One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.

Example 1:

Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:

Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]

Constraints:

The number of nodes in the list is in the range [1, 5000].
-5000 <= Node.val <= 5000
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

class Insertion_Sort_List_147 {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode();

        ListNode curr = head;

        while(curr != null) {
            ListNode prev = dummy;

            while(prev.next != null && prev.next.val < curr.next) prev = prev.next;

            ListNode next = curr.next;

            curr.next = prev.next;

            prev.next = curr;

            curr = next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Insertion_Sort_List_147 obj = new Insertion_Sort_List_147();

        ListNode four = new ListNode(4);
        ListNode three = new ListNode(3);
        ListNode five = new ListNode(5);
        ListNode two = new ListNode(2);

        four.next = three;
        three.next = five;
        five.next = two;

        ListNode res = obj.insertionSortList(four);

        while(res != null) {
            System.out.print(res.val);
            System.out.print(" ");

            res = res.next;
        }
    }

}
/*

    4 ----> 3 ----> 5



    null -> 4 -> null
    ｜
    prev


    3 --> 5
    ｜
    curr


    next = 5

    3 --> 4






next = 3 ---> 5

4 ===> null

null => 4;

3--->5;
*/




