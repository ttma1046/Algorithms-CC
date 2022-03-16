package binarysearch;
import java.util.Arrays;
/*
A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. 

Each day, we load the ship with packages on the conveyor belt (in the order given by weights). 

We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

Example 2:

Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

Example 3:

Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1

Constraints:

1 <= days <= weights.length <= 5 * 104
1 <= weights[i] <= 500
*/
class Capacity_To_Ship_Packages_Within_D_Days_1011 {
    public int shipWithinDays(int[] weights, int days) {
    	int n = weights.length;

  		int low = Arrays.stream(weights).max().getAsInt();
    	int high = Arrays.stream(weights).sum();
    	int estimatedTopCapacity = 0;

    	while (low < high) {
    		// guess the capacity of ship
    		estimatedTopCapacity = low + (high - low) / 2;

    		int currentWeight = 0; // loaded capacity of current ship
    		int estimatedDays = 1; // number of ship needed

    		// -- simulating loading the weight to ship on by one --
    		for (int weight: weights) {
    			currentWeight += weight;
    			if (currentWeight > estimatedTopCapacity) {
    				estimatedDays += 1;
    				currentWeight = weight;
    			}
    		}
    		// -- simulation ends --

    		// we need more days, so we need to increase estimated capacity to reduce num of days
    		if (estimatedDays > days)
    			low = estimatedTopCapacity + 1;
    		else
    			high = estimatedTopCapacity; 
    		// we able to ship with good num of ships, but we will need to find the optimal max capacity
    	}

    	return low;
    }

    public static void main(String[] args) {
    	Capacity_To_Ship_Packages_Within_D_Days_1011 obj = new Capacity_To_Ship_Packages_Within_D_Days_1011();
    }

    public int shipWithinDaysII(int[] weights, int days) {
  		int low = Arrays.stream(weights).max().getAsInt();
    	int high = Arrays.stream(weights).sum();
    	int mid = 0;
    	while (low < high) {
    		mid = low + (high - low) / 2;
    		if (feasible(mid, weights, days)) 
    			high = mid;
    		else
    			low = mid + 1;
    	}

    	return low;
    }

    private boolean feasible(int capacity, int[] weights, int targetDays) {
    	int days = 1;
    	int total = 0;

    	for (int weight: weights) {
    		total += weight;
    		if (total > capacity) {
    			days += 1;
    			total = weight;
    			if (days > targetDays) return false;
    		}
    	}

    	return true;
    }
}