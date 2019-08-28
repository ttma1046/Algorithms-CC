package dp;

public class KadanesAlgorithm {
    public static void main(String [] args) {
        System.out.println(new KadanesAlgorithm().calculate(new int[] {1, 2, 3, -3, 2}));
    }

    private int calculate(int [] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }

        int maxEndingHere = array[0];
        int maxSoFar = array[0];

        for (int i = 1; i < array.length; i++) {
            int number = array[i];
            maxEndingHere = Math.max(maxEndingHere + number, number);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
}


/*
[3] = 3
[3, 5] = 8
[3, 5, -9] = -1
[3, 5, -9, 1] = 1
[3, 5, -9, 1, 3] = 4
[3, 5, -9, 1, 3, -2] = 2
[3, 5, -9, 1, 3, -2, 3] = 5
[3, 5, -9, 1, 3, -2, 3, 4] = 9
[3, 5, -9, 1, 3, -2, 3, 4, 7] = 9

maxEndingHere = maxEnding + currentValue || currentVlaue
*/

class KadanesAlgorithmII {
    public static void main(String [] args) {
        System.out.println(new KadanesAlgorithmII().calculateMaxValueForSubArray(new int [] {1, 2, 3, -3, 2}));
    }

    private int calculateMaxValueForSubArray(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        int maxUntilEnd = array[0];
        int maxSoFar = array[0];

        for (int i = 1; i < array.length; i++) {
            int number = array[i];
            maxUntilEnd = Math.max(maxUntilEnd + number, number);
            maxSoFar = Math.max(maxUntilEnd, maxSoFar);
        }

        return maxSoFar;
    }
}