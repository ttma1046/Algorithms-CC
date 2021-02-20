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

    // Checks that freq[char] <= 0 meaning we have an elligible substring
    private boolean fulfilled(int[] extra) {
        boolean fulfilled = true;
        for (int f : extra) {
            if (f > 0) fulfilled = false;
        }
        return fulfilled;
    }

    // Q 0 W 1 E 2 R 3
    private int charToIdx(char c) {
        switch (c) {
        case 'Q': return 0;
        case 'W': return 1;
        case 'E': return 2;
        }
        return 3;
    }

    public int balancedStringII(String s) {
        // 1) Find freq of each first
        int N = s.length();
        int required = N / 4;

        int[] freq = new int[4];
        for (int i = 0; i < N; ++i) {
            char c = s.charAt(i);
            ++freq[charToIdx(c)];
        }

        // 2) Determine the ones we need to change
        boolean equal = true;
        for (int i = 0; i < 4; ++i) {
            if (freq[i] != required) equal = false;
            freq[i] = Math.max(0, freq[i] - required);
        }

        if (equal) return 0; // Early return if all are equal

        // 3) Use sliding window and try to track what more is needed to find smallest window
        int start = 0;
        int minLen = N; // Maximum will only ever be N


        System.out.println("freq.Q:" + freq[0]);
        System.out.println("freq.W:" + freq[1]);
        System.out.println("freq.E:" + freq[2]);
        System.out.println("freq.R:" + freq[3]);



        for (int end = 0; end < N; ++end) {
            char c = s.charAt(end);
            System.out.println("s:end: " + end + ", character:" + c);
            --freq[charToIdx(c)];


            System.out.println("freq.Q:" + freq[0]);
            System.out.println("freq.W:" + freq[1]);
            System.out.println("freq.E:" + freq[2]);
            System.out.println("freq.R:" + freq[3]);
            System.out.println("--------------------");



            while (fulfilled(freq)) {
                System.out.println("end:" + end);
                System.out.println("start:" + start);
                System.out.println("end - start + 1: " + (end - start + 1));
                minLen = Math.min(end - start + 1, minLen);
                System.out.println("minLen:" + minLen);

                System.out.println("--------------------");

                char st = s.charAt(start);
                System.out.println("s:start: " + start + ", character:" + st);

                ++freq[charToIdx(st)];
                ++start;
                System.out.println("plus:");

                System.out.println("freq.Q:" + freq[0]);
                System.out.println("freq.W:" + freq[1]);
                System.out.println("freq.E:" + freq[2]);
                System.out.println("freq.R:" + freq[3]);
                System.out.println("start:" + start);
                
                System.out.println("--------------------");

            }
        }

        return minLen;
    }


    /*

    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
    W W E Q E R Q W Q W  W  R  W  W  E  R  Q  W  E  Q

    Q 5
    W 8
    E 4
    R 3

    Q
    W => 3
    E
    R
    */

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
        new Replace_the_Substring_for_Balanced_String_1234().balancedStringII("WWEQERQWQWWRWWERQWEQ");
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
