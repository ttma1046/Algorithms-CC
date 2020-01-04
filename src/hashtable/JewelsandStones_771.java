package hashtable;

import java.util.HashMap;
import java.util.Map;

public class JewelsandStones_771 {
    public int numJewelsInStones(String J, String S) {
        if (S == null || J == null) {
            return 0;
        }

        /*
        Map<Character, Integer> count = new HashMap<Character, Integer>();
        for (Character c: S.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

         */

        int result = 0;
        for (Character j: S.toCharArray()) {
            if (J.indexOf(j) > -1) {
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new JewelsandStones_771().numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(new JewelsandStones_771().numJewelsInStones("z", "ZZ"));
    }
}
