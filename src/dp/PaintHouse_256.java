package dp;

import java.util.ArrayList;

class PaintHouse_256 {
    public int minCost(int[][] costs) {
        int length = costs.length;

        if (costs == null || costs.length <= 0) {
            return 0;
        }

        ArrayList<int[]> costsArray = new ArrayList<int[]>();

        for (int[] arr : costs) {
            costsArray.add(arr);
        }

        return minCost(costsArray, costs, length);
    }

    public int minCostRec(ArrayList<int[]> costsArray, int[][] costs, int length) {
        if (length == 1) {
            return minCost(costs[0]);
        }

        return minCostRec(costsArray.cut(length - 1), costs, length - 1) + minCost(costs[length - 1]);
    }

    private int minCost(int[] cost, int previousIndex) {
        // return cost[0] > cost[1] ? (cost[1] > cost[2] ? cost[2] : cost[1]) : cost[0];

        if (previousIndex == 0) {
            return cost[1] > cost[2] ? cost[2] : cost[1];
        } else if (previousIndex == 1) {
            return cost[0] > cost[2] ? cost[2] : cost[0];
        } else {
            return cost[0] > cost[1] ? cost[1] : cost[0];
        }
    }
}