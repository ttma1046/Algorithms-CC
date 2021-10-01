package linkedlist;

/*
You are given two non-empty linked lists representing two non-negative integers.

The digits are stored in reverse order, and each of their nodes contains a single digit.

Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]


Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
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

class Add_Two_Numbers_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prevHead = new ListNode(-1);

        ListNode res = prevHead;

        int whatleft = 0;

        while (l1 != null || l2 != null) {
            int val = whatleft;

            if (l1 != null) {
                val += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                val += l2.val;
                l2 = l2.next;
            }

            res.next = new ListNode(val % 10);
            res = res.next;
            whatleft = val / 10;
        }

        if (whatleft > 0) res.next = new ListNode(whatleft);
        return prevHead.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;

        ListNode dummy = new ListNode(-1), curr = dummy;

        while(l1 != null || l2 != null) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            carry = sum / 10;
        }

        if (carry > 0) curr.next = new ListNode(carry);
        return dummay.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);

        int carry = 0;

        ListNode curr = dummy;

        while(l1 != null || l2 != null || carry == 1) {
            int val = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;

            curr.next = new ListNode(val % 10);

            carry = val / 10;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

            curr = curr.next;
        }

        return dummy.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);

        ListNode cur = dummy;

        int carry = 0;

        while (l1 != null || l2 != null || carry == 1) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);

            int val = sum + carry;

            cur.next = new ListNode(val % 10);

            carry = val / 10;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

            cur = cur.next;
        }

        return dummy.next;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        helper(dummy, l1, l2, 0);
        return dummy.next;
    }


    private void helper(ListNode curr, ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            if (carry > 0) {
                curr.next = new ListNode(carry);
            }

            return;
        }

        if (l1 != null) {
            carry += l1.val;
            l1 = l1.next;
        }

        if (l2 != null) {
            carry += l2.val;
            l2 = l2.next;
        }

        curr.next = new ListNode(carry % 10);

        helper(curr.next, l1, l2, val / 10);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        helper(dummy, l1, l2, 0);

        return dummy.next;
    }

    private void helper(ListNode curr, ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            if (carry > 0) curr.next = new ListNode(carry);

            return;
        }

        if (l1 != null ) {
            carry += l1.val;
            l1 = l1.next;
        }

        if (l2 != null) {
            carry += l2.val;
            l2 = l2.next;
        }

        curr.next = new ListNode(carry % 10);
        helper(curr.next, l1, l2, carry / 10);
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(2, new ListNode(4, new ListNode(3)));

        ListNode b = new ListNode(5, new ListNode(6, new ListNode(4)));

        Add_Two_Numbers_2 obj = new Add_Two_Numbers_2();
        ListNode res = obj.addTwoNumbers(a, b);

        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}