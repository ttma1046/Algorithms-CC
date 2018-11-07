package array;

import java.util.HashMap;
import java.util.HashSet;

public class SockMerchant {
    static int sockMerchant(int n, int[] ar) {
        if (n <= 0 || ar.length == 0 || ar == null) {
            return 0;
        }

        HashSet hash = new HashSet();
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (hash.contains(ar[i])) {
                result++;
                hash.remove(ar[i]);
            } else {
                hash.add(ar[i]);
            }
        }

        return result;
    }

    static int sockMerchantIII(int n, int[] ar) {
        if (n <= 0 || ar.length == 0 || ar == null) {
            return 0;
        }

        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            Integer frequency = hash.get(ar[i]);
            if (frequency == null) {
                hash.put(ar[i], 1);
            } else {
                hash.put(ar[i], frequency + 1);
            }
        }

        for (Integer frequency: hash.values()) {
            result += frequency/2;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(sockMerchantIII(9, new int[] {10,20,20,10,10,30,50,10,20}));
    }
}
