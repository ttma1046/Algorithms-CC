For anyone confused about `res += j - i + 1`, here's how you can look at it:

*(Warning: This is a lengthy explanation -- TL;DR in comments)*

**Example: A = [1,2,1,2,3], K = 2**
We know that `res` should return the **total** number of contiguous subarrays with **at most** `K` different integers.
We also know that at every iteration, `j - i + 1`  gives the **length of the contiguous subarray**.
Given our example, let's see how this is handled in each valid iteration of this code (i.e. the "sliding window" part of this technique). 

Here's what we get:
* `[1]`  is **one** valid result of a contiguous subarray that has **at most** `K` different integers and has the length of **1**.
* `[1,2]`  is **one** valid result of a contiguous subarray that has **at most** `K` different integers and has the length of **2**.
* `[1,2,1]`  is **one** valid result of a contiguous subarray that has **at most** `K` different integers and has the length of **3**.
* `[1,2,1,2]` is **one** valid result of a contiguous subarray that has **at most** `K` different integers and has the length of **4**.

Then when we see the last element `3`, we will see that the only valid contiguous subarray with **at most** `K` (which is 2) different integers that can be created is:
* `[2,3]` is **one** valid result of a contiguous subarray that has **at most** `K` different integers and has the length of **2**.

So our function will return the sum of the **length** of all of those subarrays:
*  **1 + 2 + 3 + 4** which is **10** different contiguous subarrays with **at most** `K` different integers for the subarray `[1,2,1,2]`.
*  **2** which is **2** different contiguous subarrays with **at most** `K` different integers for the subarray `[2,3]`.

Here the answer is **10 + 2** which is **12** different contiguous subarrays with **at most** `K = 2` different integers.

**____________________________________________________________________________________**

**Q: Now that we know visually what the code is doing, how do we relate that back to the actual subarrays? What exactly are those different contiguous subarrays?**
Great, we have **12** different contiguous subarrays with **at most** `K = 2` different integers, but what does that actually look like logically?

`[1,2,1,2]` will produce a total of **10** different contiguous subarrays:
* `[1,2,1,2]` (**1** different contiguous subarrays with length 4)
* `[1,2,1]`, `[2,1,2]` (**2** different contiguous subarrays with length 3)
* `[1, 2]`, `[1,2]`, `[2,1]`(**3** different contiguous subarrays with length 2)
* `[1]`, `[2]`, `[1]`, `[2]` (**4** different contiguous subarrays with length 1)

`[2,3]` will produce a total of **3** different contiguous subarrays:
* `[2,3]` (**1** different contiguous subarrays with length 2)
* `[2]`, `[3]` (**2** different contiguous subarrays with length 1)

*NOTE: Remember this for a bit later. Grouping it in terms of length will help us understand a point later*

Now you may notice that `[2,3]` produced a length of 2 earlier, but can actually create **3** different subarrays.
`[2,3]` will only produce **2** *new* different contiguous subarrays because `[2]` was already accounted for in `[1,2,1,2]`.

**____________________________________________________________________________________**


**Q: Okay, I understood all that, but I still don't get why the number of contiguous subarrays is equal to the sum of lengths. Why does that work?**
We want to find **all** valid contiguous subarrays that`[1,2,1,2,3]` would produce with **at most K** different integers. 
You will notice though -- that when we say **at most K** different integers -- we only use `K` to help us find the valid windows (ex. `[1,2,1,2]` and `[2,3]`)
Once we have those valid windows though, we don't really care what `K` is (that's because **at most** means **0 to K** unique Integers, which literally means any contiguous subarray now). So, stop thinking about how `K` will affect the subarrays to understand the summation of lengths.

Okay, so now that we have our (let's call them "complete") valid subarrays `[1,2,1,2]` and `[2,3]` -- we can begin to understand why we take the summation of lengths to count all subarrays. Remember how we listed all the subarrays earlier and how I asked you to remember why we group subarrays by length?

Here's why:
`[1,2,1,2]` will produce **1** subarrays of length 4, **2** subarrays of length 3, **3** subarrays of length 2, and **4** subarray of length 1 *(see above)*
`[2,3]` will produce **1** subarrays of length 2, **2** subarray of length 1 *(see above)*

Do you notice anything?

`[1,2,1,2]` = **1 + 2 + 3 + 4** (sum of our **1** subarrays of length 4, **2** subarray of length 3, etc.) = **10**
`[2,3]` = **1 + 2** (*Special case:  this creates **1** subarray of length 2, and **2** subarray of length 2, but since our `[2]` one was accounted for already, we only get **2** new subarrays so subtract **1**) **- 1** = **2**

When we summed the different lengths *(see above where we listed the iterations)*, we also get the same growth (i.e. **1 + 2 + 3 + 4**)!

So another way to understand this in the context of this problem is, that the code above will produce valid (sliding) window (like `[1,2]`, `[1,2,1]`, `[1,2,1,2]`).
As we expand the length of that window, we can sum the length of those windows to get our different combinations because if our "complete" window was `[1,2,1,2]`,
we could do **1 + 2 + 3 + 4**  (or length of `[1]` +  length of `[1,2]` + length of `[1,2,1]` + length of `[1,2,1,2]`).

We also noticed that for `[2,3]`, by only adding **2**, we were able to ignore that duplicate subarray. The sliding window did not return `[2]` because the window expanded to `[1,2,1,2,3]` -- invalidating the window and then compressed the window to `[2,3]` by moving `i` forward. This allowed us to skip those duplicate subarrays. You can expand this to other examples including where `K` is larger. The fact that our sliding window compresses  by moving forward `i` will allow the lower summations to be ignored (i.e. our duplicate subarrays).

**____________________________________________________________________________________**

**Q: Woah, that was a lot to take in. Does learning this help me with other subarray counting?**
You'll be happy to know that it does! This actually comes up frequently in other Leetcode questions that require us to count different combinations of subarrays.
Grouping the subarrays in terms of length can really help us understand that summing up the lengths can give us the total subarrays.
If you're still not convinced, write out a few examples of arrays of different lengths. The number of subarrays you can create can be related to the length.

Arrays of length 5 will produce **1 + 2 + 3 + 4 + 5** different contiguous subarrays.
Arrays of length 6 will produce **1 + 2 + 3 + 4 + 5 + 6** different contiguous subarrays.
*...and the list goes on*

**Alternative Formula:**
If you're given an array of length `n`, it will produce `(n *(n+1))/2` total contiguous subarrays (same as doing the sum above).
This is used often in other questions where we know our array size of `n` and so we don't need to add `[1...n]` incrementally like this problem.

This is just one way to look at this, I'm sure there are more ways to break down counting subarrays. Keep reading around until something resonates with you. Best of luck!

I can explain res += j - i + 1 :)

suppose initial window [a] then subarrays that ends with this element are [a]--> 1
now we expand our window [a,b] then subarrays that ends with this new element are [b], [a,b] -->2
now we expand our window [a,b,c] then subarrays that ends with this new element are [c], [b, c], [a,b,c] -->3
now we expand our window [a,b,c,d] and let suppose this is not valid window so we compress window from left side to make it valid window
[b,c,d] then subarrays that ends with this new element are [d], [c,d], [b,c,d] -->3

You can observe that we are only considering subarrays with new element in it which auto. eliminate the counting of duplicate subarrays that we already considered previously.
And surprisingly the number of sub-arrays with this new element in it is equal to the length of current window.

|1|       2|          1|         2|       3|
|-|--------|-----------|----------|--------|
|[1]| [2]|        [1]|       [2]|    [3]|
| |  [1,2]|    [2,1]|    [1,2]| [2,3]|
| |          |[1,2,1] |[2,1,2]|        |
| |||                           [1,2,1,2]||

The intuition behind `atMostK(A, K) - atMostK(A, K - 1);`

This problem is a hard ask, until you realize that we\'ve actually solved this problem before in other sliding window problems. Generally, the sliding window problems have some kind of aggregate, atMost k, largest substring, min substring with k etc. They\'re always "given an array or string, find some computed sub problem" value. 

So how do we use this our advantage? Well, the ask: `different integers in that subarray is exactly K` is exactly this. We can rewrite the problem to something like this:

`subArrayExactly(K) = subArrayAtMost(K) - subArrayAtMost(K - 1)`.  This is basically saying, give me the amount of subarrays we can form with *at least* 3, and give me the amount of subarrays we can form with *at least* 2, and the diff between the two will be *only* subarrays at 3 (since we have eliminated everything 2 and under).

Example:        
Input: A = [1,2,1,2,3], K = 2
Output: 7

subArrayAtMostK = 12
if k = 2, there are 12 possible subarrays that are at least 2 values.
This is the array possibilities we create, where the count is the end - start (see below code example), since a sub problem will contribute to the overall amount of possibilities. You see below for the AtMostK problems if this concept is confusing. The other trick here is that in other atMostK problems, they ask for length, but length can also be a substitute for amount of sub problems, since the length of any given range, say `1212`, also constitutes 4 different subarray possibilities.
[1, 12, 121, 1212, 23]

1 + 2 + 3 + 4 + 2 = 12

subArrayAtMostK - 1 = 5
since k = 1, every subarray is a single element, so the length of the array. There are by definition, only 5 different subarrays that can be formed.
[1, 2, 1, 2, 3]
1 + 1 + 1 + 1 + 1 = 5

So the amount of subarrays possible with at least 2 - the amount of subarrays with at least 1 = the exactly subarrays that contain only 2, since we have stripped out answers with only 1.

12 - 5 = 7;

I\'m not sure I would ever make this connection in an interview, but very clever.

If you need help understanding the atMostK concept, these problems will help:
https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/

https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
```java
    public int subarraysWithKDistinct(int[] A, int K) {
        int i = helper(A, K);
        int j = helper(A, K - 1);

        return i - j;
    }

    private int helper(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int start = 0;
        int end = 0;
        int len = 0;
        Map<Integer, Integer> map = new HashMap<>();

        List<String> pairs = new ArrayList<>();

        while (end < A.length) {
            int endNum = A[end];

            map.put(endNum, map.getOrDefault(endNum, 0) + 1);

            end++;

            while (map.size() > K) {
                int startNum = A[start];

                map.put(startNum, map.get(startNum) - 1);

                if (map.get(startNum) == 0) {
                    map.remove(startNum);
                }

                start++;
            }

            len += end - start;

            // just a hack to visualize the pairs we build for learning and comprehension 
            StringBuilder sb = new StringBuilder();
            for (int i = start; i < end; i++) {
                sb.append(A[i]);
            }
            pairs.add(sb.toString());
        }

        System.out.println(pairs.toString());

        return len;
    }
```