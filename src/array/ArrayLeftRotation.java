package array;

import java.io.*;
import java.util.*;


/*
A left rotation operation on an array shifts each of the array's elements  unit to the left. For example, if  left rotations are performed on array , then the array would become .

Given an array  of  integers and a number, , perform  left rotations on the array. Return the updated array to be printed as a single line of space-separated integers.

Function Description

Complete the function rotLeft in the editor below. It should return the resulting array of integers.

rotLeft has the following parameter(s):

An array of integers .
An integer , the number of rotations.
Input Format

The first line contains two space-separated integers  and , the size of  and the number of left rotations you must perform.
The second line contains  space-separated integers .

Constraints

Output Format

Print a single line of  space-separated integers denoting the final state of the array after performing  left rotations.

Sample Input

5 4
1 2 3 4 5
Sample Output

5 1 2 3 4
Explanation

When we perform  left rotations, the array undergoes the following sequence of changes:
 */
public class ArrayLeftRotation {

    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
        if (a == null || a.length == 0) return a;
        if (d <= 0) { return a; }

        for(int j = 1; j <= d; j++) {
            int temp = a[0];
            for(int i = 0; i < a.length - 1; i++) {
                a[i] = a[i + 1];
            }
            a[a.length - 1] = temp;
        }
        return a;
    }

    int[] leftRotate(int a[], int rotatetime, int length) {
        int i, j, k, temp;
        // arr[] =
        // { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }
        // n = 12
        // d = 3
        // GCD of 12 and 3 is 3
        // first set
        // { 4, 2, 3, 7, 5, 6, 10, 8, 9, 1, 11, 12 }
        // second set
        // { 4, 5, 3, 7, 8, 6, 10, 11, 9, 1, 2, 12 }
        // third set
        // { 4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3 }
        for(i = 0; i < GreatestCommonDivisor(rotatetime, length); i++)
        {
            /* move i-th values of blocks */
            temp = a[i];
            j = i;

            while(true) {
                k = j + rotatetime;
                if (k >= length)
                    k = k - length;
                if (k == i)
                    break;
                a[j] = a[k];
                j = k;
            }

            a[j] = temp;
        }

        return a;
    }

    public static int GreatestCommonDivisor(int p, int q)
    {
        if (q == 0)
        {
            return p;
        }

        int r = p % q;

        return GreatestCommonDivisor(q, r);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] result = rotLeft(a, d);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
