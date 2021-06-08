```
There are two sorted arrays `nums1` and `nums2` of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be `O(log (m+n))`.

You may assume `nums1` and `nums2` cannot be both empty.

Example 1:

    nums1 = [1, 3]
    nums2 = [2]

    The median is 2.0

Example 2:

    nums1 = [1, 2]
    nums2 = [3, 4]

    The median is (2 + 3)/2 = 2.5

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

```

The overall run time complexity should be O(log (m+n)).

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Example 3:

Input: nums1 = [0,0], nums2 = [0,0]
Output: 0.00000

Example 4:

Input: nums1 = [], nums2 = [1]
Output: 1.00000

Example 5:

Input: nums1 = [2], nums2 = []
Output: 2.00000

### Constraints:

* nums1.length == m
* nums2.length == n
* 0 <= m <= 1000
* 0 <= n <= 1000
* 1 <= m + n <= 2000
* -106 <= nums1[i], nums2[i] <= 106

To solve this problem, we need to understand "What is the use of median?".

In statistics, the median is used for dividing a set into two equal length subsets, that one subset is always greater than the other.

If we understand the use of median for dividing, we are very close to the answer.

First let's cut A into two parts at a random position i:

|left_A|right_A|
|------|-------|
|A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]|

Since **A** has **m** elements, so there are **m + 1** kinds of cutting( i = 0 ~ m ).
And we know: len(left_A) = __i__, len(right_A) = __m - i__ .

Note: when i = 0 , left_A is empty, and when i = m , right_A is empty.

With the same way, cut B into two parts at a random position j:

|left_B|right_B|
|------|-------|
B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]

And we know: len(left_B) = __j__, len(right_B) = __n - j__ .
Note: when j = 0 , left_B is empty, and when j = n , right_B is empty.

Put left_A and left_B into one set, and put right_A and right_B into another set. Let's name them left_part and right_part :
|left_Part|right_Part|
|------|-------|
A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]

If we can ensure:

    1) len(left_part) == len(right_part)
    2) max(left_part) <= min(right_part)

   1|4 7 8
    -----
 1 2 3 4|5 6

   1 4|7
 1 2 3|4 5 6

then we divide all elements in {A, B} into two parts with equal length, and one part is always greater than the other. Then median = (max(left_part) + min(right_part))/2.

To ensure these two conditions, we just need to ensure:

    (1) i + j == m - i + n - j (or: m - i + n - j + 1)
        if n >= m, we just need to set: i = 0 ~ m, j = (m + n + 1)/2 - i
    (2) B[j-1] <= A[i] and A[i-1] <= B[j]

ps.1 For simplicity, I presume `A[i-1],B[j-1],A[i],B[j]` are always valid even if `i=0/i=m/j=0/j=n`. I will talk about how to deal with these edge values at last.

ps.2 Why `n >= m`? Because I have to make sure j is non-nagative since 0 <= i <= m and j = (n + m + 1)/2 - i. 
If `n < m`, then j may be nagative, that will lead to wrong result.

So, all we need to do is:

    Searching i in [0, m], where j = (m + n + 1)/2 - i, to find an object `i` that:
        B[j-1] <= A[i] and A[i-1] <= B[j], 

And we can do a binary search following steps described below:

    <1> Set left = 0, right = m, then start searching in [left, right]

    <2> Set i = (left + right)/2, j = (m + n + 1)/2 - i

    <3> Now we have len(left_part) == len(right_part). 

    And there are only 3 situations that we may encounter:
        <a> B[j-1] <= A[i] and A[i-1] <= B[j]
            Means we have found the object `i`, so stop searching.
        <b> B[j-1] > A[i]
            Means A[i] is too small. We must `ajust` i to get `B[j-1] <= A[i]`.
            Can we `increase` i?
                Yes. Because when i is increased, j will be decreased.
                So B[j-1] is decreased and A[i] is increased, and `B[j-1] <= A[i]` may
                be satisfied.
            Can we `decrease` i?
                `No!` Because when i is decreased, j will be increased.
                So B[j-1] is increased and A[i] is decreased, and B[j-1] <= A[i] will
                be never satisfied.
            So we must `increase` i. That is, we must ajust the searching range to
            [i+1, right]. So, set left = i+1, and goto <2>.
        <c> A[i-1] > B[j]
            Means A[i-1] is too big. And we must `decrease` i to get `A[i-1]<=B[j]`.
            That is, we must ajust the searching range to [left, i-1].
            So, set right = i-1, and goto <2>.

When the object i is found, the median is:

    * max(A[i-1], B[j-1]) (when m + n is odd)
    * (max(A[i-1], B[j-1]) + min(A[i], B[j])) / 2 (when m + n is even)

Now let's consider the edges values `i=0,i=m,j=0,j=n` where `A[i-1],B[j-1],A[i],B[j]` may not exist. Actually this situation is easier than you think.

i = 0:

     |6 8
     -----  
  1 2 3 4|5 7

    |6 8
    ------ 
  1 2 3 4|5

i = m
    1 2 3|
   -------
  2|5 6 7 8

j = 0
   1 2 3 4 5|
  -----------
  |7 8 9 10 11

j = m
   |5 8 9 10
   ----------
   1 2 3 4 5|

What we need to do is ensuring that max(left_part) <= min(right_part).
So, if i and j are not edges values(means A[i-1],B[j-1],A[i],B[j] all exist),
then we must check both B[j-1] <= A[i] and A[i-1] <= B[j].
But if some of `A[i-1], B[j-1], A[i], B[j]` don't exist, then we don't need to check one(or both) of these two conditions.
For example, if i=0, then A[i-1] doesn't exist, then we don't need to check A[i-1] <= B[j]. So, what we need to do is:

    Searching i in [0, m], to find an object `i` that:
        (j == 0 or i == m or B[j-1] <= A[i]) and
        (i == 0 or j == n or A[i-1] <= B[j])
        where j = (m + n + 1)/2 - i
And in a searching loop, we will encounter only three situations:

    <a> (j == 0 or i == m or B[j-1] <= A[i]) and
        (i == 0 or j = n or A[i-1] <= B[j])
        Means i is perfect, we can stop searching.

    <b> j > 0 and i < m and B[j - 1] > A[i]
        Means i is too small, we must increase it.

    <c> i > 0 and j < n and A[i - 1] > B[j]
        Means i is too big, we must decrease it.

Thank @Quentin.chen , him pointed out that: `i < m ==> j > 0 and i > 0 ==> j < n`. Because:

    m <= n, i < m ==> j = (m+n+1)/2 - i > (m+n+1)/2 - m >= (2*m+1)/2 - m >= 0    
    m <= n, i > 0 ==> j = (m+n+1)/2 - i < (m+n+1)/2 <= (2*n+1)/2 <= n

So in situation <b> and <c>, we don't need to check whether j > 0 and whether j < n.

Below is the accepted code:

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    if (m > n) { // to ensure m<=n
        int[] temp = nums1; nums1 = nums2; nums2 = temp;
        int tmp = m; m = n; n = tmp;
    }
    
    int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
    while (iMin <= iMax) {
        int i = (iMin + iMax) / 2;
        int j = halfLen - i;
        if (i < iMax && nums2[j - 1] > nums1[i]) {
            iMin = i + 1; // i is too small
        } else if (i > iMin && nums1[i - 1] > nums2[j]) {
            iMax = i - 1; // i is too big
        } else { // i is perfect
            int maxLeft = 0;
            
            if (i == 0) { 
                maxLeft = nums2[j - 1]; 
            } else if (j == 0) { 
                maxLeft = nums1[i - 1]; 
            } else { 
                maxLeft = Math.max(nums1[i - 1], nums2[j - 1]); 
            }

            if ((m + n) % 2 == 1) { 
                return maxLeft; 
            }

            int minRight = 0;
            if (i == m) { 
                minRight = nums2[j]; 
            } else if (j == n) { 
                minRight = nums1[i]; 
            } else { 
                minRight = Math.min(nums2[j], nums1[i]); 
            }

            return (maxLeft + minRight) / 2.0;
        }
    }
    return 0.0;
}
```