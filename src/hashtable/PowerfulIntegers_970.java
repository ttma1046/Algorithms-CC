package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PowerfulIntegers_970 {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        List<Integer> result = new ArrayList<Integer>();
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();

        for (int i = 0,j = 0; Math.pow(x, i) + Math.pow(y, j) < bound; i++, j++) {
            count.put(i, j);
        }

        return null;
    }
}
