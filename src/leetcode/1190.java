Solution 1: Brute Force
Here is the brute force solution, which seems really easy to write.
Nothing more to talk about.

Time O(N^2), Space O(N)


Python

    def reverseParentheses(self, s):
        res = ['']
        for c in s:
            if c == '(':
                res.append('')
            elif c == ')':
                res[len(res) - 2] += res.pop()[::-1]
            else:
                res[-1] += c
        return "".join(res)
C++

    string reverseParentheses(string s) {
        vector<int> opened;
        string res;
        for (int i = 0; i < s.length(); ++i) {
            if (s[i] == '(')
                opened.push_back(res.length());
            else if (s[i] == ')') {
                int j = opened.back();
                opened.pop_back();
                reverse(res.begin() + j, res.end());
            } else {
                res += s[i];
            }
        }
        return res;
    }

Solution 2: Wormholes
Intuition
Nice. I got a green accpeted with solution 1.
Now before move on, let us check the solutions in the discuss.

Hey hey hey wait, ALL solutions are BRUTE FORCE ?
Hmmmm... why not O(N)?

Fine fine fine, here comes an easy O(N) solution.

Explanation
In the first pass,
use a stack to find all paired parentheses,
I assume you can do this.

Now just imgine that all parentheses are wormholes.
As you can see in the diagram,
the paired parentheses are connected to each other.

image

First it follows the left green arrrow,
go into the left wormhole and get out from the right wormhole.
Then it iterates the whole content between parentheses.
Finally it follows the right arrow,
go into the left wormhole,
get out from the right wormhole and finish the whole trip.

So in the second pass of our solution,
it traverses through the paired parentheses
and generate the result string res.

i is the index of current position.
d is the direction of traversing.


Complexity
Time O(N) for two passes
Space O(N)


Java:

    public String reverseParentheses(String s) {
        int n = s.length();
        Stack<Integer> opened = new Stack<>();
        int[] pair = new int[n];
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(')
                opened.push(i);
            if (s.charAt(i) == ')') {
                int j = opened.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0, d = 1; i < n; i += d) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = pair[i];
                d = -d;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
C++:

    string reverseParentheses(string s) {
        int n = s.length();
        vector<int> opened, pair(n);
        for (int i = 0; i < n; ++i) {
            if (s[i] == '(')
                opened.push_back(i);
            if (s[i] == ')') {
                int j = opened.back();
                opened.pop_back();
                pair[i] = j;
                pair[j] = i;
            }
        }
        string res;
        for (int i = 0, d = 1; i < n; i += d) {
            if (s[i] == '(' || s[i] == ')')
                i = pair[i], d = -d;
            else
                res += s[i];
        }
        return res;
    }
Python:

    def reverseParentheses(self, s):
        opened = []
        pair = {}
        for i, c in enumerate(s):
            if c == '(':
                opened.append(i)
            if c == ')':
                j = opened.pop()
                pair[i], pair[j] = j, i
        res = []
        i, d = 0, 1
        while i < len(s):
            if s[i] in '()':
                i = pair[i]
                d = -d
            else:
                res.append(s[i])
            i += d
        return ''.join(res)