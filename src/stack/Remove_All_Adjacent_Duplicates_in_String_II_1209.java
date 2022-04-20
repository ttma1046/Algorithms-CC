package stack;
/*
You are given a string s and an integer k,

a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them,

causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation:
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"

Constraints:

1 <= s.length <= 105
2 <= k <= 104
s only contains lower case English letters.
*/
class Remove_All_Adjacent_Duplicates_in_String_II_1209 {
    public String removeDuplicates(String s, int k) {
        int i = 0;
        int n = s.length();
        int count[] = new int[n];

        char[] stack = s.toCharArray();

        for (int j = 0; j < n; ++j, ++i) {
            stack[i] = stack[j];

            if (i > 0 && stack[i - 1] == stack[j])
                count[i] = count[i - 1] + 1;
            else
                count[i] = 1;

            if (count[i] == k)
                i -= k;
        }

        return new String(stack, 0, i);
    }

    public String removeDuplicates(String s, int k) {
        int[] count = new int[s.length()];
        StringBuilder sb = new StringBuilder();

        for(char c : s.toCharArray()) {
            sb.append(c);
            int last = sb.length() - 1;

            if (last > 0 && sb.charAt(last) == sb.charAt(last - 1)) {
                count[last] = 1 + count[last - 1];
            } else {
                count[last] = 1;
            }
            
            if(count[last] >= k) 
                sb.delete(sb.length() - k, sb.length());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Remove_All_Adjacent_Duplicates_in_String_II_1209 obj = new Remove_All_Adjacent_Duplicates_in_String_II_1209();
        obj.removeDuplicates("deeedbbcccbdaa", 3);
    }
}

/*
Intuition
If you read my post last month about 1047. Remove All Adjacent Duplicates In String
you cannot solve this problem easier.

Solution 1: Two Pointers
java

    public String removeDuplicates(String s, int k) {
        int i = 0, n = s.length(), count[] = new int[n];
        char[] stack = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            stack[i] = stack[j];
            count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
            if (count[i] == k) i -= k;
        }
        return new String(stack, 0, i);
    }

Solution 2: Stack
Save the character c and its count to the stack.
If the next character c is same as the last one, increment the count.
Otherwise push a pair (c, 1) into the stack.
I used a dummy element ('#', 0) to avoid empty stack.

Java
By @sherstoi.
Need to import from javafx in case you don't know that.
Also surprised to notice that leetcode don't let me create Map.entry

import javafx.util.Pair;
class Solution {
    public String removeDuplicates(String s, int k) {
        Deque<Pair<Integer, Character>> stack = new ArrayDeque<>();
        stack.push(new Pair(0, '#'));

        for (char c : s.toCharArray()) {
            if (stack.peek().getValue() != c) {
                stack.push(new Pair(1, c));
            } else {
                int count = stack.peek().getKey() + 1;
                stack.pop();
                if (count != k)
                    stack.push(new Pair(count, c));
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            for (int i = 0; i < stack.peek().getKey(); i++) {
                stringBuilder.append(stack.peek().getValue());
            }
            stack.pop();
        }

        return stringBuilder.reverse().toString();
    }
}

Complexity
Time O(N) for one pass
Space O(N)
*/