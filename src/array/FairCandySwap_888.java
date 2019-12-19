package array;

import java.util.HashSet;
import java.util.Set;

class FairCandySwap_888 {
    /*
    A - x + y = B - y + x;
    2y = B - A - 2x
    2x = A - B + 2y
    x = (A - B) / 2 + y
    y = (B - A) / 2 - x;
    */

    A-y+x = B-x+y
    x = (B - A)/2 + y

    public int[] FairCandySwap(int[] A, int[] B) {
        if (A.length <= 0 || B.length <=0) return new int[0];
        int sumA = 0, sumB = 0;
        for (int x: A) { sumA += x; }
        for (int x: B) { sumB += x; }
        int delta = (sumA - sumB) / 2;
        Set<Integer> myset = new HashSet<Integer>();

        for (int x: A) {
            myset.add(x);
        }

        for (int y: B) {
            if (myset.contains(delta + y)) {
                return new int[2] {delta + y, y}
            }
        }

        return new int[0];
    }



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

        Set<Integer> setB = new HashSet<Integer>();
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