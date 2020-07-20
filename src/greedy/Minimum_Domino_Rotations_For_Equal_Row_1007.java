package greedy;

import java.util.Arrays;
import java.util.HashSet;

/*
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the i-th domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

If it cannot be done, return -1.



Example 1:



Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
Output: 2
Explanation:
The first figure represents the dominoes as given by A and B: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
Example 2:

Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
Output: -1
Explanation:
In this case, it is not possible to rotate the dominoes to make one row of values equal.


Note:

1 <= A[i], B[i] <= 6
2 <= A.length == B.length <= 20000
*/

class Minimum_Domino_Rotations_For_Equal_Row_1007 {
    /*
        Count the occurrence of all numbers in A and B,
        and also the number of domino with two same numbers.

        Try all possibilities from 1 to 6.
        If we can make number i in a whole row,
        it should satisfy that countA[i] + countB[i] - same[i] = n

        Take example of
        A = [2,1,2,4,2,2]
        B = [5,2,6,2,3,2]

        countA[2] = 4, as A[0] = A[2] = A[4] = A[5] = 2
        countB[2] = 3, as B[1] = B[3] = B[5] = 2
        same[2] = 1, as A[5] = B[5] = 2

        We have countA[2] + countB[2] - same[2] = 6,
        so we can make 2 in a whole row.

        Time O(N), Space O(1)
    */
    public int minDominoRotationsI(int[] A, int[] B) {
        int[] countA = new int[7], countB = new int[7], same = new int[7];
        int n = A.length;
        for (int i = 0; i < n; ++i) {
            countA[A[i]]++;
            countB[B[i]]++;
            if (A[i] == B[i]) {
                same[A[i]]++;
            }
        }

        for (int i  = 1; i < 7; ++i) {
            if (countA[i] + countB[i] - same[i] == n) {
                return n - Math.max(countA[i], countB[i]);
            }
        }

        return -1;
    }

    /* Time O(N), Space O(1) */
    public int minDominoRotationsII(int[] A, int[] B) {
        int n = A.length;
        for (int i = 0, a = 0, b = 0; i < n && (A[i] == A[0] || B[i] == A[0]); ++i) {
            System.out.println("First");
            if (A[i] != A[0]) a++;
            if (B[i] != A[0]) b++;
            System.out.println("a:" + a + " b:" + b);
            if (i == n - 1) return Math.min(a, b);
        }

        for (int i = 0, a = 0, b = 0; i < n && (A[i] == B[0] || B[i] == B[0]); ++i) {
            System.out.println("Second");
            if (A[i] != B[0]) a++;
            if (B[i] != B[0]) b++;
            System.out.println("a:" + a + " b:" + b);
            if (i == n - 1) return Math.min(a, b);
        }
        return -1;
    }


    /*
    Return min number of rotations
    if one could make all elements in A or B equal to x.
    Else return -1.
    */
    public int check(int x, int[] A, int[] B, int n) {
        // how many rotations should be done
        // to have all elements in A equal to x
        // and to have all elements in B equal to x
        int rotations_a = 0, rotations_b = 0;
        for (int i = 0; i < n; i++) {
            // rotations coudn't be done
            if (A[i] != x && B[i] != x) return -1;
            // A[i] != x and B[i] == x
            else if (A[i] != x) rotations_a++;
            // A[i] == x and B[i] != x
            else if (B[i] != x) rotations_b++;
        }
        // min number of rotations to have all
        // elements equal to x in A or B
        return Math.min(rotations_a, rotations_b);
    }

    public int minDominoRotations(int[] A, int[] B) {
        int n = A.length;
        int rotations = check(A[0], B, A, n);
        // If one could make all elements in A or B equal to A[0]
        if (rotations != -1 || A[0] == B[0]) return rotations;
        // If one could make all elements in A or B equal to B[0]
        else return check(B[0], B, A, n);
    }


    /*
    Find intersection set s of all {A[i], B[i]}
    s.size = 0, no possible result.
    s.size = 1, one and only one result.
    s.size = 2, it means all dominoes are [a,b] or [b,a], try either one.
    s.size > 2, impossible.
    */
    public int minDominoRotationsIII(int[] A, int[] B) {
        HashSet<Integer> mySet = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        for (int item : mySet) {
            System.out.println(item);
        }

        int[] countA = new int[7], countB = new int[7];
        for (int i = 0; i < A.length; ++i) {
            mySet.retainAll(new HashSet<>(Arrays.asList(A[i], B[i])));
            System.out.println("mySet size = " + mySet.size());
            for (int item : mySet) {
                System.out.println("item:" + item);
            }
            countA[A[i]]++;
            countB[B[i]]++;
            System.out.println("countA[" + A[i] + "] = " + countA[A[i]]);
            System.out.println("countB[" + B[i] + "] = " + countB[B[i]]);
        }

        for (int i : mySet) {
            System.out.println(i);
            return A.length - Math.max(countA[i], countB[i]);
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Minimum_Domino_Rotations_For_Equal_Row_1007().minDominoRotationsIII(new int [] {2, 1, 2, 4, 2, 2}, new int [] {5, 2, 6, 2, 3, 2}));

        System.out.println(new Minimum_Domino_Rotations_For_Equal_Row_1007().minDominoRotationsIII(new int [] {3, 5, 1, 2, 3}, new int [] {2, 6, 3, 3, 4}));
    }
}