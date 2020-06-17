Binary search solution
See also a sorting solution below.

The value we are looking for is somewhere between 1 and maxValue (m).
Now use Binary search to speed up the process.

go up if the sum is too small
go down if the sum is too big
When we are done with binary search, l and r are equal, but it might happen that we have not exactly reached the target.
Check if l-1 (should get us below the target) leads to the sum closer to the target.

Java, 4ms:

class Solution {
    public int findBestValue(int[] arr, int target) {
        int l, r, mi, s=0, m=-1;
        for(int v:arr) { s += v; m=Math.max(m,v); }

        if(s<=target) return m; // return the max value since we will keep all nums as is

        for(l=1,r=m;l<r;) {
            mi=(l+r)/2;
            s=0;
            for(int v:arr) s += (v>mi)?mi:v;
            if(s>=target) r=mi;
            else          l=mi+1;
        }
        // check if we are 1 step off the target 
        int s1=0,s2=0;
        for(int v:arr) {
            s1 += (v>l)?(l):v;
            s2 += (v>l-1)?(l-1):v;
        }
        
        return (Math.abs(s2-target) <= Math.abs(s1-target)) ? l-1 : l;
    }
}
C++, 16ms

class Solution {
public:
    int findBestValue(vector<int>& arr, int target) {
        int l, r, mi, s=0, m=-1;
        for(int v:arr) s += v, m=max(m,v);

        if(s<=target) return m; // return the max value since we will keep all nums as is

        for(l=1,r=m;l<r;) {
            mi=(l+r)/2;
            s=0;
            for(int v:arr) s += (v>mi)?mi:v;
            if(s>=target) r=mi;
            else          l=mi+1;
        }
        // check if we are 1 step off the target 
        int s1=0,s2=0;
        for(int v:arr) s1 += (v>l)?(l):v, s2 += (v>l-1)?(l-1):v;
        
        return (abs(s2-target) <= abs(s1-target)) ? l-1 : l;
    }
};
UPDATE:

Sorting solution
I got comments that sorting solution can be more intuitive. It's maybe shorter but binary search is easier to apply as a pattern (at least for me).
Well, sorting leads to O(NLogN) speed while binary search gives O(NLog(Max(A))) so it's similar.
However, in practice It's slightly slower on given tests.

Intuition:

We will need to replace the highest values with some value v
v must be >= the highest value of the array. In other words: v >= max(arr)
We should pop the highest value from arr while the current result is above the target:
while(target < sum + max(arr)*numOfRemoved)
Sorting is done to speed up the search for the higest values
Then we simply calculate v to replace each removed element and get as close to the target as possible
In the end of "pop high values" process we will split the sorted array in 2 parts:

          Splitted the sorted values to left and right parts:
          a,b,c,d   |   X,Y,Z
          -------       =====
          keep          replace each elem
          as is         with v
  sum:    =sum-X-Y-Z    =v*numOfElems 
          left part + right part = target
Values in the left part will stay as is. Their sum is the total sum - sum of the removed.
Values in the right part will be replaced with v and we keep their number in removed.

left part + right part = target
left part + v*removed  = target
v = (target - left part)/removed
When we do the division, v is rounded down. I check also v+1 to cover the undesired rounding though I'm pretty sure that a sophisticated formula can do the same shorter (I prefer simpler things).

Java, 5ms

    public int findBestValue(int[] arr, int target) {
        int s=0, m=-1;
        for(int v:arr) { s += v; m=Math.max(m,v); }
        if(s<=target) return m; // return the max value since we will keep all nums as is

        Arrays.sort(arr);
        
        int removed=0, lastIx=arr.length-1;
        while(lastIx>=0 && target < s + removed*arr[lastIx]) {
            removed++;
            s -= arr[lastIx];
            lastIx--;
        }
        int v=(target-s)/removed; // rounded down
        if(Math.abs(target-s-removed*v) <=
           Math.abs(target-s-removed*(v+1))) // check v+1 to compensate round down
            return v;
        return v+1;
    }
C++, 24ms

    int findBestValue(vector<int>& arr, int target) {
        int s=0, m=-1;
        for(int v:arr) s += v, m=max(m,v);
        if(s<=target) return m; // return the max value since we will keep all nums as is

        sort(arr.begin(),arr.end());
        
        int removed=0, lastIx=arr.size()-1;
        while(lastIx>=0 && target < s + removed*arr[lastIx]) {
            removed++;
            s -= arr[lastIx];
            lastIx--;
        }
        int v=(target-s)/removed; // rounded down
        if(abs(target-s-removed*v) <=
           abs(target-s-removed*(v+1))) // check v+1 to compensate round down
            return v;
        return v+1;
    }