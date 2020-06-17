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
C++

    string removeDuplicates(string s, int k) {
        int i = 0, n = s.length();
        vector<int> count(n);
        for (int j = 0; j < n; ++j, ++i) {
            s[i] = s[j];
            count[i] = i > 0 && s[i - 1] == s[j] ? count[i - 1] + 1 : 1;
            if (count[i] == k) i -= k;
        }
        return s.substr(0, i);
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
C++

    string removeDuplicates(string s, int k) {
        vector<pair<int, char>> stack = {{0, '#'}};
        for (char c: s) {
            if (stack.back().second != c) {
                stack.push_back({1, c});
            } else if (++stack.back().first == k)
                stack.pop_back();
        }
        string res;
        for (auto & p : stack) {
            res += string(p.first, p.second);
        }
        return res;
    }
Python:

    def removeDuplicates(self, s, k):
        stack = [['#', 0]]
        for c in s:
            if stack[-1][0] == c:
                stack[-1][1] += 1
                if stack[-1][1] == k:
                    stack.pop()
            else:
                stack.append([c, 1])
        return ''.join(c * k for c, k in stack)

Complexity
Time O(N) for one pass
Space O(N)