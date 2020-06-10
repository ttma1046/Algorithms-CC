/*
You are a product manager and currently leading a team to develop a new product. 
Unfortunately, the latest version of your product fails the quality check. 

Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. 

Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:

Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version. 
*/
/*
    The isBadVersion API is defined in the parent class VersionControl.
    boolean isBadVersion(int version); 
*/
package binarysearch;
public class FirstBadVersion_278 {
    public int firstBadVersion(int n) {
        if (n <= 0) {
            return 0;
        }

        int left = 1;
        int right = n;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (isBadVersion(mid) == true) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        	
	    return left;
    }

    private boolean isBadVersion(int versionNumber) {
        return versionNumber <= 5 ? false : true;
    }

    public static void main(String[] args) {
        System.out.println(new FirstBadVersion_278().firstBadVersion(10));
    }
               
    // left  1    1     2     3     4
    // right 10   4     4     4     4
    // mid   5    2     3     3     3
    //      true false false false false 
}
