#### Approach 1: Linear Scan

**Intuition**

Checking every index for `target` exhausts the search space, so it must work.

**Algorithm**

First, we do a linear scan of `nums` from the left, `break`ing when we find
an instance of `target`. If we never `break`, then `target` is not present,
so we can return the \"error code\" of `[-1, -1]` early. Given that we did find
a valid left index, we can do a second linear scan, but this time from the
right. In this case, the first instance of `target` encountered will be the
rightmost one (and because a leftmost one exists, there is guaranteed to also
be a rightmost one). We then simply return a list containing the two located
indices.

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        // find the index of the leftmost appearance of `target`.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                targetRange[0] = i;
                break;
            }
        }

        // if the last loop did not find any index, then there is no valid range
        // and we return [-1, -1].
        if (targetRange[0] == -1) {
            return targetRange;
        }

        // find the index of the rightmost appearance of `target` (by reverse
        // iteration). it is guaranteed to appear.
        for (int j = nums.length-1; j >= 0; j--) {
            if (nums[j] == target) {
                targetRange[1] = j;
                break;
            }
        }

        return targetRange;
    }
}
```

**Complexity Analysis**

* Time complexity : `O(n)`

This brute-force approach examines each of the `n` elements of `nums` exactly twice, so the overall runtime is linear.

* Space complexity : `O(1)`

The linear scan method allocates a fixed-size array and a few integers, so it has a constant-size memory footprint.

#### Approach 2: Binary Search

**Intuition**

Because the array is sorted, we can use binary search to locate the left
and rightmost indices.

**Algorithm**

The overall algorithm works fairly similarly to the linear scan approach,
except for the subroutine used to find the left and rightmost indices
themselves. Here, we use a modified binary search to search a sorted array,
with a few minor adjustments. First, because we are locating the leftmost (or
rightmost) index containing `target` (rather than returning `true` if we
find `target`), the algorithm does not terminate as soon as we find a match.
Instead, we continue to search until `lo == hi` and they contain some index
at which `target` can be found.

The other change is the introduction of the `left` parameter, which is a
boolean indicating what to do in the event that `target == nums[mid]`; if
`left` is `true`, then we \"recurse\" on the left subarray on ties. Otherwise,
we go right. To see why this is correct, consider the situation where we find
`target` at index `i`. The leftmost `target` cannot occur at any index
greater than `i`, so we never need to consider the right subarray. The same
argument applies to the rightmost index.

The first animation below shows the process for finding the leftmost index,
and the second shows the process for finding the index right of the rightmost
index.

!?!../Documents/34_Search_for_a_Range_left.json:1280,720!?!

!?!../Documents/34_Search_for_a_Range_right.json:1280,720!?!

```java
class Solution {
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }
}
```

**Complexity Analysis**

* Time complexity : `O(log N)`

    Here we invoke binary search twice. 
    Let's compute time complexity with the help of 
    [master theorem](https://en.wikipedia.org/wiki/Master_theorem_(analysis_of_algorithms)) 
    `T(N) = aT * (N/b) + Theta(N^d)`.
    The equation represents dividing the problem 
    up into `a` subproblems of size `N/b` in `Theta(N^d)` time. 
    Here at step there is only one subproblem `a = 1`, its size 
    is a half of the initial problem `b = 2`, 
    and all this happens in a constant time `d = 0`.
    That means that `log_b{a} = d` and hence we're dealing with 
    [case 2](https://en.wikipedia.org/wiki/Master_theorem_(analysis_of_algorithms)#Case_2_example)
    that results in `mathcal{O}(n^{log_b{a}} log^{d + 1} N)`
    = `O(log N)` time complexity.

    * Space complexity : `O(1)`

    All work is done in place, so the overall memory usage is constant.