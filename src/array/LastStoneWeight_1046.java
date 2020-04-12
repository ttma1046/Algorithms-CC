package array;

import java.util.Arrays;

class LastStoneWight_1046 {
    public int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length <= 0) {
            return 0;
        }


        if (stones.length == 1) {
            return stones[0];
        }

        Arrays.sort(stones);
        for (int i = stones.length - 1; i > 0; i--) {
            stones[i - 1] = stones[i] - stones[i - 1];
            if (i > 0) {
                Arrays.sort(stones);
            }
        }

        return stones[0];
    }

    public static void main(String[] args) {
        System.out.println(new LastStoneWight_1046().lastStoneWeight(new int[] { 2, 7, 4, 1, 8, 1 }));
    }
}