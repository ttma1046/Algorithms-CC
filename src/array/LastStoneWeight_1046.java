package array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

class LastStoneWeight_1046 {
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
        System.out.println(new LastStoneWeight_1046().lastStoneWeightII(new int[] { 2, 7, 4, 1, 8, 1 }));
    }

    public int lastStoneWeightII(int[] stones) {
        if (stones == null || stones.length <= 0) {
            return 0;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i: stones) {
            heap.add(i);
        }

        while(heap.size() > 1) {
            int stone1 = heap.remove();
            int stone2 = heap.remove();

            if (stone1 != stone2) {
                heap.add(stone1 - stone2);
            }
        }

        return heap.isEmpty() ? 0 : heap.remove();
    }
}