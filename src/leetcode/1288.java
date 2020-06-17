Intuition
Imagine that, after removing all covered intervals,
all intervals must have different bounds,
After sorting, their left and right bound are increasing at the same time.


Explanation
Sort the array, and note the previous left and right bound.
For evert interval v,
if v[0] > left && v[1] > right,
there is a new uncovered interval,
so we increment ++res.

Just a tip, in Java, C++ and Python,
they sort in different ways.


Complexity
Time O(NlogN)
Space O(N)


Test Case
The test cases set only contains random useless test cases.
(In my opinion the problem maker didn't do his work.)

Also the solution judger is wrong,
input = [[1,4],[1,8],[3,6],[2,8]]
my solution output = 1
but expected = 2
(This has been fixed)

Here are some useful small test cases for debugging.
[[1,2],[1,3]]
[[1,3],[1,8],[5,8]]
[[1,6],[4,6],[4, 8]


Java:

    public int removeCoveredIntervals(int[][] A) {
        int res = 0, left = -1, right = -1;
        Arrays.sort(A, (a, b) -> a[0] - b[0]);
        for (int[] v : A) {
            if (v[0] > left && v[1] > right) {
                left = v[0];
                ++res;
            }
            right = Math.max(right, v[1]);
        }
        return res;
    }
C++

    int removeCoveredIntervals(vector<vector<int>>& A) {
        int res = 0, left = -1, right = -1;
        sort(A.begin(), A.end());
        for (auto& v: A) {
            if (v[0] > left && v[1] > right) {
                left = v[0];
                ++res;
            }
            right = max(right, v[1]);
        }
        return res;
    }
Python:

    def removeCoveredIntervals(self, A):
        res = right = 0
        A.sort(key=lambda a: (a[0], -a[1]))
        for i, j in A:
            res += j > right
            right = max(right, j)
        return res