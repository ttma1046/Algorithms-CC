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
