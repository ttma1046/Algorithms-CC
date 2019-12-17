package array;

import java.util.HashMap;
import java.util.Map;

class DeckCards_914 {
    public boolean hasGroupsSizeX(int[] deck) {
        int result = -1;
        if (deck == null || deck.length <= 0) {
            return false;
        }

        if (deck.length == 1) {
            return false;
        }

        int [] hash = new int[10000];
        for (int x: deck) {
            hash[x]++;
        }

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > 0) {
                if (result == -1) {
                    result = hash[i];
                } else {
                    result = gcd(result, hash[i]);
                }
            }
        }

        return result > 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public boolean hasGroupsSizeXII(int[] deck) {
        Map<Integer, Integer> count = new HashMap<>();
        int res = 0;
        for (int i : deck) count.put(i, count.getOrDefault(i, 0) + 1);
        for (int i : count.values()) res = gcd(i, res);
        return res > 1;
    }

    public static void main(String[] args) {
        System.out.println("test");
    }
}