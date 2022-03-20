package binarysearch;
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
public class FirstBadVersion_278 {
    public int firstBadVersionII(int n) {
        if (n <= 0) return 0;

        int low = 1, high = n, mid = 0;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (!isBadVersion(mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public int firstBadVersion(int n) {
        if (n <= 0) {
            return 0;
        }

        int left = 1;
        int right = n;
        int mid = -1;
        int result = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (isBadVersion(mid) ) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private boolean isBadVersion(int versionNumber) {
        return versionNumber > 3;
    }

    int firstBadVersionV(int n) {
        int start = 1, end = n;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (!isBadVersion(mid))
                start = mid  + 1;
            else
                end = mid - 1;
        }

        return start;
    }

    int firstBadVersionVI(int versionNumber) {
        int start = 1, end = versionNumber;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (!isBadVersion(mid))
                start = mid  + 1;
            else
                end = mid;
        }

        return start;
    }

    int firstBadVersionVII(int versionNumber) {
        if (versionNumber == 1) return isBadVersion(versionNumber) ? 1 : -1;
        int start = 1, end = versionNumber;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (!isBadVersion(mid))
                start = mid;
            else
                end = mid;
        }
        if (isBadVersion(start)) return start;
        if (isBadVersion(end)) return end;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new FirstBadVersion_278().firstBadVersion(5));
        System.out.println(new FirstBadVersion_278().firstBadVersionII(5));
        System.out.println(new FirstBadVersion_278().firstBadVersionV(5));
        System.out.println(new FirstBadVersion_278().firstBadVersionVI(5));
        System.out.println(new FirstBadVersion_278().firstBadVersionVII(5));
    }

    // left  1    1     2     3     4
    // right 10   4     4     4     4
    // mid   5    2     3     3     3
    //      true false false false false
}
