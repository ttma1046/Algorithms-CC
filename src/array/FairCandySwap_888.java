package array;

import java.util.HashSet;
import java.util.Set;

class FairCandySwap_888 {
    public int[] fairCandySwap(int[] A, int[] B) {
        if (A.length <= 0  || B.length <= 0) return null;
        
        int sumA = 0;
        int sumB = 0;

        for (int x: A) {
            sumA += x;
        }

        for (int x: B) {
            sumB += x;
        }

        int delta = sumB - sumA / 2;

        var setB = new HashSet<Integer>();
        for (int x: B) { setB.add(x); }
        for (int x: A) {
            if (setB.contains(x + delta)) {
                return new int[] {x, x + delta};
            }
        }
        
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}