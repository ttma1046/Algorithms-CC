package sweepline;

/*
Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Elements that do not appear in arr2 should be placed at the end of arr1 in ascending order.



Example 1:

Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]
Example 2:

Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
Output: [22,28,8,6,17,44]


Constraints:

1 <= arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
All the elements of arr2 are distinct.
Each arr2[i] is in arr1.
*/
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
    int[] table = new int[1001];

    for (int n : arr1)
        table[n]++;

    int i = 0;
    int[] res = new int[arr1.length];
    for (int n : arr2)
        while(table[n]-- > 0)
            res[i++] = n;

    for (int n = 0; n < table.length; n++)
        while(table[n]-- > 0)
            res[i++] = n;

    return res;
    }
    // Time: O(1)
    // Space: O(1)
    
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int n : arr1) map.put(n, map.getOrDefault(n, 0) + 1);

        int i = 0;
        for(int n : arr2) {
            for(int j = 0; j < map.get(n); j++)
                arr1[i++] = n;

            map.remove(n);
        }

        for(int n : map.keySet())
            for(int j = 0; j < map.get(n); j++)
                arr1[i++] = n;

        return arr1;
    }
    // Time: O(NlogN)
    // Space: O(N)
}