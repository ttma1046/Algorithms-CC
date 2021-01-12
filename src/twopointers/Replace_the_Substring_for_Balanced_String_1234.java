package twopointers;
/*
Intuition
We want a minimum length of substring,
which leads us to the solution of sliding window.
Specially this time we don't care the count od elements inside the window,
we want to know the count outside the window.


Explanation
One pass the all frequency of "QWER".
Then slide the windon in the string s.

Imagine that we erase all character inside the window,
as we can modyfy it whatever we want,
and it will always increase the count outside the window.

So we can make the whole string balanced,
as long as max(count[Q],count[W],count[E],count[R]) <= n / 4.


Important
Does i <= j + 1 makes more sense than i <= n.
Strongly don't think, and i <= j + 1 makes no sense.

Answer the question first:
Why do we need such a condition in sliding window problem?

Actually, we never need this condition in sliding window solution
(Check all my other solutions link at the bottom).

Usually count the element inside sliding window,
and i won't be bigger than j because nothing left in the window.

The only reason that we need a condition is in order to prevent index out of range.
And how do we do that? Yes we use i < n

Does i <= j + 1 even work?
When will i even reach j + 1?
Does i <= j + 1 work better than i <= j?

Please upvote for this important tip.
Also let me know if there is any unclear, glad to hear different voices.
But please, have a try, and show the code if necessary.

Some people likes to criticize without even a try,
and solve the problem by talking.
Why talk to me? Talk to the white board.


Complexity
Time O(N), one pass for counting, one pass for sliding window
Space O(1)


Java
*/

class Replace_the_Substring_for_Balanced_String_1234 {
    public int balancedString(String s) {
        int[] count = new int[128];
        int n = s.length(), res = n, i = 0, k = n / 4;
        for (int j = 0; j < n; ++j) {
            ++count[s.charAt(j)];
        }
        for (int j = 0; j < n; ++j) {
            --count[s.charAt(j)];
            while (i < n && count['Q'] <= k && count['W'] <= k && count['E'] <= k && count['R'] <= k) {
                res = Math.min(res, j - i + 1);
                ++count[s.charAt(i++)];
            }
        }
        return res;
    }
}


/*
More Similar Sliding Window Problems
Here are some similar sliding window problems.
Also find more explanations.
Good luck and have fun.

Count Number of Nice Subarrays
Replace the Substring for Balanced String
Max Consecutive Ones III
Binary Subarrays With Sum
Subarrays with K Different Integers
Fruit Into Baskets
Shortest Subarray with Sum at Least K
Minimum Size Subarray Sum
*/
