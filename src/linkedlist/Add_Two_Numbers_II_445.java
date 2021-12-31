package linkedlist;

/**
 * Definition for singly-linked list.
 */
/*
You are given two non-empty linked lists representing two non-negative integers.

The most significant digit comes first and each of their nodes contains a single digit.

Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:

Input: l1 = [7,2,4,3], l2 = [5,6,4]
Output: [7,8,0,7]

Example 2:

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [8,0,7]

Example 3:

Input: l1 = [0], l2 = [0]
Output: [0]

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.

Follow up: Could you solve it without reversing the input lists?
*/

public class ListNode {
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

class Add_Two_Numbers_II_445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stackOne = new Stack<>();
        Stack<Integer> stackTwo = new Stack<>();

        while(l1 != null) {
            stackOne.push(l1.val);
            l1 = l1.next;
        }

        while(l2 != null) {
            stackTwo.push(l2.val);
            l2 = l2.next;
        }

        ListNode res = null;

        int carry = 0;

        while(stackOne.size() > 0 || stackTwo.size() > 0 || carry != 0) {
            int p1 = stackOne.size() == 0 ? 0 : stackOne.pop();
            int p2 = stackTwo.size() == 0 ? 0 : stackTwo.pop();

            int sum = p1 + p2 + carry;

            ListNode curr = new ListNode(sum % 10);
            carry = sum / 10;
            curr.next = res;
            res = curr;
        }

        return res;
    }

    public ListNode reverseList(ListNode head) {
        ListNode last = null;
        while (head != null) {
            // keep the next node
            ListNode tmp = head.next;
            // reverse the link
            head.next = last;
            // update the last node and the current node
            last = head;
            head = tmp;
        }
        return last;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // reverse lists
        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode head = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            // get the current values
            int x1 = l1 != null ? l1.val : 0;
            int x2 = l2 != null ? l2.val : 0;

            // current sum and carry
            int val = (carry + x1 + x2) % 10;
            carry = (carry + x1 + x2) / 10;

            // update the result: add to front
            ListNode curr = new ListNode(val);
            curr.next = head;
            head = curr;

            // move to the next elements in the lists
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            curr.next = head;
            head = curr;
        }

        return head;
    }

    public static void main(String[] args) {
        Add_Two_Numbers_II_445 obj = new Add_Two_Numbers_II_445();

        obj.addTwoNumbers();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n1 = 0, n2 = 0;
        ListNode curr1 = l1, curr2 = l2;

        while(curr1 != null) {
            curr1 = curr1.next;
            n1++;
        }

        while(curr2 != null) {
            curr2 = curr2.next;
            n2++;
        }

        curr1 = l1;
        curr2 = l2;
        ListNode head = null;

        while(n1 > 0 && n2 > 0) {
            int val = 0;
            if (n1 >= n2) {
                val += curr1.val;
                curr1 = curr1.next;
                --n1;
            }

            if (n1 < n2) {
                val += curr2.val;
                curr2 = curr2.next;
                --n2;
            }

            ListNode curr = new ListNode(val);
            curr.next = head;
            head = curr;
        }

        curr1 = head;
        head = null;
        int carry = 0;
        while(curr1 != null) {
            int val = (carry + curr1.val) % 10;
            carry = (carry + curr1.val) / 10;

            ListNode curr = new ListNode(val);
            curr.next = head;
            head = curr;

            curr1 = curr1.next;
        }

        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            curr.next =  head;
            head = curr;
        }

        return head;
    }

    /*
    Time complexity: \mathcal{O}(N_1 + N_2), where N_1 + N_2 is a number of elements in both lists.

    Space complexity: \mathcal{O}(1) space complexity without taking the output list into account, and \mathcal{O}(\max(N_1, N_2)) to store the output list.
    */
}