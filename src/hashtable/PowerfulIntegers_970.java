package hashtable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerfulIntegers_970 {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> mySet = new HashSet<>();

        for (int a = 1; a < bound; a *= x) {
            for (int b = 1; a + b <= bound; b *= y) {
                mySet.add(a + b);
                if(y == 1) break;
            }
            if(x == 1) break;
        }

        return new ArrayList<>(mySet);
    }
}
