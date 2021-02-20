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

QQQE

Q3
E1

j = 0 => 1

=>
Q1
E1

res = 2

Q2
E1

i = 0 -> 1

j = 1 -> 2

Q1
E1

res = 2

Q2
E1

i 1 => 2

j = 2 => 3

Q2
E0
*/

class Replace_the_Substring_for_Balanced_String_1234 {
    public int balancedString(String s) {

        int[] count = new int[4];

        int n = s.length(), res = n, i = 0, k = n / 4;

        for (int j = 0; j < n; ++j) {
            if (s.charAt(j) == 'Q') ++count[0];
            if (s.charAt(j) == 'W') ++count[1];
            if (s.charAt(j) == 'E') ++count[2];
            if (s.charAt(j) == 'R') ++count[3];
        }

        System.out.println("j => count[0] Q:" + count[0]);
        System.out.println("j => count[1] W:" + count[1]);
        System.out.println("j => count[2] E:" + count[2]);
        System.out.println("j => count[3] R:" + count[3]);

        for (int j = 0; j < n; ++j) {
            System.out.println("j:" + j + ", s.charAt(j):" + s.charAt(j));

            if (s.charAt(j) == 'Q')
                --count[0];
            if (s.charAt(j) == 'W')
                --count[1];
            if (s.charAt(j) == 'E')
                --count[2];
            if (s.charAt(j) == 'R')
                --count[3];

            System.out.println("j => count[0] Q:" + count[0]);
            System.out.println("j => count[1] W:" + count[1]);
            System.out.println("j => count[2] E:" + count[2]);
            System.out.println("j => count[3] R:" + count[3]);

            while (i < n && count[0] <= k && count[1] <= k && count[2] <= k && count[3] <= k) {
                System.out.println("i:" + i + ", s.charAt(i):" + s.charAt(i));
                res = Math.min(res, j - i + 1);

                System.out.println("res:" + res);

                if (s.charAt(i) == 'Q')
                    ++count[0];
                if (s.charAt(i) == 'W')
                    ++count[1];
                if (s.charAt(i) == 'E')
                    ++count[2];
                if (s.charAt(i) == 'R')
                    ++count[3];
                System.out.println("i => count[0]: Q" + count[0]);
                System.out.println("i => count[1]: W" + count[1]);
                System.out.println("i => count[2]: E" + count[2]);
                System.out.println("i => count[3]: R" + count[3]);
                System.out.println("i:before" + i);

                i++;

                System.out.println("i:after" + i);
            }
        }

        System.out.println("count['Q']:" + count[0]);
        System.out.println("count['W']:" + count[1]);
        System.out.println("count['E']:" + count[2]);
        System.out.println("count['R']:" + count[3]);

        return res;
    }

    public static void main(String[] args) {
        new Replace_the_Substring_for_Balanced_String_1234().balancedString("WWEQERQWQWWRWWERQWEQ");
    }
}

/*
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
W W E Q E R Q W Q W  W  R  W  W  E  R  Q  W  E  Q
              j        
i

res = 8
  i
                j
                  j
    i
                    j

*/

/*
WWEQERQWQWWRWWERQWEQ

Q 5
W 8
E 4
R 3


WWEQERQWQRRREWERQWEQ

Q 5
W 5
E 5
R 5
*/


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
