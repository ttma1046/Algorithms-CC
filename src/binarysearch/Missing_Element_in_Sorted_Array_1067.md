## Solution

#### Approach 1: One Pass

**Intuition**

The problem is similar to [First Missing Positive](https://leetcode.com/articles/first-missing-positive/)
and the naive idea would be to solve it in a similar way 
by one pass approach.

Let's first assume that one has a function `missing(idx)`
that returns how many numbers are missing until the element
at index `idx`. 

![fig](./Missing_Element_in_Sorted_Array_1067/function.png)

With the help of such a function the solution is
straightforward :

- Find an index such that `missing(idx - 1) < k <= missing(idx)`.
In other words, that means that kth missing number is in-between
`nums[idx - 1]` and `nums[idx]`. 

    One even could 
    compute a difference between kth missing number and 
    `nums[idx - 1]`. First, there are `missing(idx - 1)`
    missing numbers until `nums[idx - 1]`. 
    Second, all `k - missing(idx - 1)` missing numbers from
    `nums[idx - 1]` to kth missing are _consecutive ones_,
    because all of them are less than `nums[idx]` and hence
    there is nothing to separate them.
    Together that means that kth smallest is
    larger than `nums[idx - 1]` by `k - missing(idx - 1)`.

    - Return kth smallest `nums[idx - 1] + k - missing(idx - 1)`.

![fic](./Missing_Element_in_Sorted_Array_1067/algor.png)

> The last thing to discuss is how to implement `missing(idx)` function.

Let's consider an array element at index `idx`. If there is no numbers
missing, the element should be equal to `nums[idx] = nums[0] + idx`.
If k numbers are missing, the element should be equal to
`nums[idx] = nums[0] + idx + k`. 
Hence the number of missing elements is equal to
`nums[idx] - nums[0] - idx`.

![pic](./Missing_Element_in_Sorted_Array_1067/missing.png)

**Algorithm**

- Implement `missing(idx)` function that returns how many numbers
are missing until array element with index `idx`.
Function returns `nums[idx] - nums[0] - idx`.

- Find an index such that `missing(idx - 1) < k <= missing(idx)`
by a linear search.

- Return kth smallest `nums[idx - 1] + k - missing(idx - 1)`.

**Implementation**

```java
class Solution {
  // Return how many numbers are missing until nums[idx]
  int missing(int idx, int[] nums) {
    return nums[idx] - nums[0] - idx;
  }

  public int missingElement(int[] nums, int k) {
    int n = nums.length;
    // If kth missing number is larger than 
    // the last element of the array
    if (k > missing(n - 1, nums))
      return nums[n - 1] + k - missing(n - 1, nums);

    int idx = 1;
    // find idx such that 
    // missing(idx - 1) < k <= missing(idx)
    while (missing(idx, nums) < k) idx++;

    // kth missing number is greater than nums[idx - 1]
    // and less than nums[idx]
    return nums[idx - 1] + k - missing(idx - 1, nums);
  }
}
```

**Complexity Analysis**

* Time complexity: `\mathcal{O}(N)` since in the worst case 
it's one pass along the array.

* Space complexity: `\mathcal{O}(1)` since it's a constant space solution.

---
#### Approach 2: Binary Search

**Intuition**

Approach 1 uses the linear search and 
doesn't profit from the fact that array is _sorted_.
One could replace the linear search by a [binary one](https://leetcode.com/articles/binary-search/) 
and 
reduce the time complexity from `\mathcal{O}(N)` 
down to `\mathcal{O}(\log N)`.

> The idea is to find the leftmost element such that 
the number of missing numbers until this element 
is less or equal to k.

![fif](./Missing_Element_in_Sorted_Array_1067/binary.png)

**Algorithm**

- Implement `missing(idx)` function that returns how many numbers
are missing until array element with index `idx`.
Function returns `nums[idx] - nums[0] - idx`.

- Find an index such that `missing(idx - 1) < k <= missing(idx)`
by a _binary search_.

- Return kth smallest `nums[idx - 1] + k - missing(idx - 1)`.

**Implementation**

```java
class Solution {
  // Return how many numbers are missing until nums[idx]
  int missing(int idx, int[] nums) {
    return nums[idx] - nums[0] - idx;
  }

  public int missingElement(int[] nums, int k) {
    int n = nums.length;
    // If kth missing number is larger than 
    // the last element of the array
    if (k > missing(n - 1, nums))
      return nums[n - 1] + k - missing(n - 1, nums);

    int left = 0, right = n - 1, pivot;
    // find left = right index such that 
    // missing(left - 1) < k <= missing(left)
    while (left != right) {
      pivot = left + (right - left) / 2;

      if (missing(pivot, nums) < k) left = pivot + 1;
      else right = pivot;
    }

    // kth missing number is greater than nums[idx - 1]
    // and less than nums[idx]
    return nums[left - 1] + k - missing(left - 1, nums);
  }
}
```

**Complexity Analysis**

* Time complexity: `\mathcal{O}(\log N)` since it's a binary search algorithm
in the worst case when the missing
number is less than the last element of the array.

* Space complexity : `\mathcal{O}(1)` since it's a constant space solution."
