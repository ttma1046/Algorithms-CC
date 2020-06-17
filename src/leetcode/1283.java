Explanation
Binary search the result.
If the sum > threshold, the divisor is too small.
If the sum <= threshold, the divisor is big enough.

Complexity
Time O(NlogM), where M = max(A)
Space O(1)


Java:

    public int smallestDivisor(int[] A, int threshold) {
        int left = 1, right = (int)1e6;
        while (left < right) {
            int m = (left + right) / 2, sum = 0;
            for (int i : A)
                sum += (i + m - 1) / m;
            if (sum > threshold)
                left = m + 1;
            else
                right = m;
        }
        return left;
    }
C++:

    int smallestDivisor(vector<int>& A, int threshold) {
        int left = 1, right = 1e6, m, sum;
        while (left < right) {
            m = (left + right) / 2, sum = 0;
            for (int i : A)
                sum += (i + m - 1) / m;
            if (sum > threshold)
                left = m + 1;
            else
                right = m;
        }
        return left;
    }
Python:

    def smallestDivisor(self, A, threshold):
        l,r = 1, max(A)
        while l < r:
            m = (l + r) / 2
            if sum((i + m - 1) / m for i in A) > threshold:
                l = m + 1
            else:
                r = m
        return l

More Good Binary Search Problems
Here are some similar binary search problems.
Also find more explanations.
Good luck and have fun.

Divide Chocolate
Capacity To Ship Packages In N Days
Koko Eating Bananas
Minimize Max Distance to Gas Station
Split Array Largest Sum