
package leetcode;
import java.util.Arrays;
/*
Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.

Every house can be warmed, as long as the house is within the heater's warm radius range.

Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.

Notice that all the heaters follow your radius standard, and the warm radius will the same.



Example 1:

Input: houses = [1,2,3], heaters = [2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
Example 2:

Input: houses = [1,2,3,4], heaters = [1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
Example 3:

Input: houses = [1,5], heaters = [2]
Output: 3


Constraints:

1 <= houses.length, heaters.length <= 3 * 104
1 <= houses[i], heaters[i] <= 109
*/
public class Heaters_475 {
    public int findRadius(int[] houses, int[] heaters) {
        if (houses == null || houses.length == 0)
            return 0;

        if (heaters == null || heaters.length == 0)
            return Integer.MAX_VALUE;

        Arrays.sort(houses);
        Arrays.sort(heaters);

        int radious = 0;
        int i = 0;
        int j = 0;

        for(i = 0; i < houses.length; i++) {
            while (j < heaters.length - 1 &&
                    Math.abs(heaters[j] - houses[i]) >= Math.abs(heaters[j + 1] - houses[i]))
                j++;


            radious = Math.max(radious, Math.abs(heaters[j] - houses[i]));
        }

        return radious;
    }

    public int findRadiusII(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int res = 0;
        int i = 0;
        for (int house : houses) {
            while (i + 1 < heaters.length && house > (heaters[i] + heaters[i + 1]) / 2 ) {
                i++;
            }
            res = Math.max(res, Math.abs(heaters[i] - house));
        }
        return res;
    }

    public int findRadiusIII(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = 0;

        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0) {
                index = ~index;
                int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
                int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

                result = Math.max(result, Math.min(dist1, dist2));
            }
        }

        return result;
    }

    public static void main(String[] args) {
    	Heaters_475 obj = new Heaters_475();
    	int[] intArr = {10,20,15,22,35};

    	System.out.println(Arrays.binarySearch(intArr, 1)); // -

    	int test = Arrays.binarySearch(intArr, 11); // -2
    	System.out.println(~test); // -1

    }
}
