Intuition
Have you read this? 992. Subarrays with K Different Integers


Explanation
Exactly K times = at most K times - at most K - 1 times


Complexity
Time O(N) for one pass
Space O(1)


Java:

    public int numberOfSubarrays(int[] A, int k) {
        return atMost(A, k) - atMost(A, k - 1);
    }

    public int atMost(int[] A, int k) {
        int res = 0, i = 0, n = A.length;
        for (int j = 0; j < n; j++) {
            k -= A[j] % 2;
            while (k < 0)
                k += A[i++] % 2;
            res += j - i + 1;
        }
        return res;
    }
C++:

    int numberOfSubarrays(vector<int>& A, int k) {
        return atMost(A, k) - atMost(A, k - 1);
    }

    int atMost(vector<int>& A, int k) {
        int res = 0, i = 0, n = A.size();
        for (int j = 0; j < n; j++) {
            k -= A[j] % 2;
            while (k < 0)
                k += A[i++] % 2;
            res += j - i + 1;
        }
        return res;
    }
Python:

    def numberOfSubarrays(self, A, k):
        def atMost(k):
            res = i = 0
            for j in xrange(len(A)):
                k -= A[j] % 2
                while k < 0:
                    k += A[i] % 2
                    i += 1
                res += j - i + 1
            return res

        return atMost(k) - atMost(k - 1)

More Similar Sliding Window Problems
Here are some similar sliding window problems.
Also find more explanations.
Good luck and have fun.

Count Number of Nice Subarrays
Replace the Substring for Balanced String
Max Consecutive Ones III
Subarrays with K Different Integers
Fruit Into Baskets