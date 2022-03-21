package binarysearch;


/*
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4

Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23

Constraints:

1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
*/
class Koko_Eating_Bananas_875 {
    public int minEatingSpeed(int[] piles, int h) {
        int speed = 1;

        while(true) {
            int hoursspend = 0;
            for (int pile : piles) {
                hoursspend += Math.ceil((double)pile / speed);

                if (hoursspend > h) break;
            }

            if (hoursspend <= h) {
                return speed;
            } else {
                speed++;
            }
        }
    }

    /*
    Complexity Analysis

    Let nn be the length of input array pilespiles and mm be the upper bound of elements in pilespiles.

    Time complexity: O(nm)

    For each eating speed speedspeed, we iterate over pilespiles and calculate the overall time, which takes O(n) time.
    Before finding the first workable eating speed, we must try every smaller eating speed. Suppose in the worst-case scenario (when the answer is mm), we have to try every eating speed from 11 to mm, that is a total of mm iterations over the array.
    To sum up, the overall time complexity is O(nm)
    Space complexity: O(1)

    For each eating speed speedspeed, we iterate over the array and calculate the total hours Koko spends, which costs constant space.
    Therefore, the overall space complexity is O(1).
    */

    public int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = 1;
        for(int pile : piles)
            if (pile > high)
                high = pile;

        int mid = 0;

        while(low < high) {
            mid = low + (high - low) / 2;

            int hourspend = 0;

            for (int pile : piles)
                hourspend += (pile - 1) / mid + 1;

            if (hourspend <= h)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }

    /*
    Complexity Analysis

    Let nn be the length of the input array pilespiles and mm be the maximum number of bananas in a single pile from pilespiles.

    Time complexity: O(n * logm)

    The initial search space is from 11 to mm, it takes logm comparisons to reduce the search space to 1.
    For each eating speed middlemiddle, we traverse the array and calculate the overall time Koko spends,
    which takes O(n) for each traversal.
    To sum up, the time complexity is O(n⋅logm).
    Space complexity: O(1)

    For each eating speed middle, we iterate over the array and calculate the total hours Koko spends, which costs constant space.
    Therefore, the overall space complexity is O(1).
    */

    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1;
        int hi = 1_000_000_000;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (!possible(piles, H, mi))
                lo = mi + 1;
            else
                hi = mi;
        }

        return lo;
    }

    // Can Koko eat all bananas in H hours with eating speed K?
    public boolean possible(int[] piles, int H, int K) {
        int time = 0;
        for (int p : piles)
            time += (p - 1) / K + 1;
        return time <= H;
    }

    public static void main(String[] args) {
        Koko_Eating_Bananas_875 obj = new Koko_Eating_Bananas_875();
    }
}