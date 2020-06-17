Intuition
Change the input of string s and t into an array of difference.
Then it's a standard sliding window problem.


Complexity
Time O(N) for one pass
Space O(1)


Java:

    public int equalSubstring(String s, String t, int k) {
        int n = s.length(), i = 0, j;
        for (j = 0; j < n; ++j) {
            k -= Math.abs(s.charAt(j) - t.charAt(j));
            if (k < 0) {
                k += Math.abs(s.charAt(i) - t.charAt(i));
                ++i;
            }
        }
        return j - i;
    }
C++:

    int equalSubstring(string s, string t, int k) {
        int n = s.length(), i = 0, j;
        for (j = 0; j < n; ++j) {
            if ((k -= abs(s[j] - t[j])) < 0)
                k += abs(s[i] - t[i++]);
        }
        return j - i;
    }
Python:

    def equalSubstring(self, s, t, cost):
        i = 0
        for j in xrange(len(s)):
            cost -= abs(ord(s[j]) - ord(t[j]))
            if cost < 0:
                cost += abs(ord(s[i]) - ord(t[i]))
                i += 1
        return j - i + 1