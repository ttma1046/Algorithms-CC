Idea
Compute maxEndHere and maxStartHere arrays and also find overall max along with them.
Now, evaluate the case where 1-element can be eliminated, that is at each index, we can make use of maxEndHere[i-1]+maxStartHere[i+1]

Thought process
This approach is a slight improvisation on the idea of https://leetcode.com/problems/maximum-subarray/. Basically, the difference here is we can eliminate 1 number and still can continue with expanding our subarray. So imagine a subarray where you removed 1 element, then it forms two subarrays ending at prev index and starting at next index. We know how to get maxEndHere from the max sum subarray problem for each index. If we reverse our thinking to follow the same logic to solve for subarray at next index, we should be able to see computing maxStartHere is just backwards of maxEndHere. So now at each index, it is just about looking at prev and next numbers from the respective arrays to get overall max.

public int maximumSum(int[] a) {
        int n = a.length;
        int[] maxEndHere = new int[n], maxStartHere = new int[n];
        int max = a[0];
        maxEndHere[0] = a[0];
        for(int i=1; i < n; i++){
            maxEndHere[i] = Math.max(a[i], maxEndHere[i-1]+a[i]);
            max = Math.max(max, maxEndHere[i]);
        }
        maxStartHere[n-1] = a[n-1];
        for(int i=n-2; i >= 0; i--)
            maxStartHere[i] = Math.max(a[i], maxStartHere[i+1]+a[i]);
        for(int i=1; i < n-1; i++)
            max = Math.max(max, maxEndHere[i-1]+maxStartHere[i+1]);
        return max;
    }