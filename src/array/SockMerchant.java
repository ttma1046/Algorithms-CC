package array;

import java.util.HashMap;
import java.util.HashSet;

public class SockMerchant {
    static int sockMerchant(int n, int[] ar) {
        if (n <= 0 || ar.length == 0 || ar == null) {
            return 0;
        }

        HashSet<Integer> set = new HashSet<Integer>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (set.contains(ar[i])) {
                result++;
                set.remove(ar[i]);
            } else {
                set.add(ar[i]);
            }
        }

        return result;
    }

    static int sockMerchantIII(int n, int[] ar) {
        if (n <= 0 || ar.length == 0 || ar == null) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            Integer frequency = map.get(ar[i]);
            if (frequency == null) {
                map.put(ar[i], 1);
            } else {
                map.put(ar[i], frequency + 1);
            }
        }

        for (Integer frequency : map.values()) {
            result += frequency / 2;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(sockMerchantIII(9, new int[] { 10, 20, 20, 10, 10, 30, 50, 10, 20 }));
    }
}
