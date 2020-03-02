package recursion;

import java.util.ArrayList;
import java.util.Collections;

public class StackofBoxes_8point13_Recur {
    int createStack(ArrayList<Box> boxes) {
        Collections.sort(boxes, new BoxComparator());
        int[] stackMap = new int[boxes.size()];
        return createStack(boxes, null, 0, stackMap);
    }

    int createStack(ArrayList<Box> boxes, Box bottom, int offset, int[] stackMap) {
        if (offset >= boxes.size()) {
            return 0; // Base case;
        }

        /* height with this bottom */
        Box newBottom = boxes.get(offset);
        int heightWithBottom = 0;
        if (bottom == null || newBottom.canBeAbove(bottom)) {
            if (stackMap[offset] == 0) {
                stackMap[offset] = createStack(boxes, newBottom, offset + 1, stackMap);
                stackMap[offset] += newBottom.height;
            }

            heightWithBottom = stackMap[offset];
        }

        /* without this bottom */
        int heightWithoutBottom = createStack(boxes, bottom, offset + 1, stackMap);

        /* Return better of two options. */
        return Math.max(heightWithBottom, heightWithoutBottom);
    }

    public boolean canBeAbove(Box b) {
        if (b == null) {
            return true;
        }
        if (width < b.width && height < b.height && depth < b.depth) {
            return true;
        }
        return false;       
    }

    public static void main(String[] args) {
        Box[] boxList = { 
            new Box(6, 4, 4), 
            new Box(8, 6, 2), 
            new Box(5, 3, 3), 
            new Box(7, 8, 3), 
            new Box(4, 2, 2), 
            new Box(9, 7, 3)
        };
        ArrayList<Box> boxes = new ArrayList<Box>();
        for (Box b : boxList) {
            boxes.add(b);
        }

        int height = new StackofBoxes_8point13_Recur().createStack(boxes);
        System.out.println(height);
    }
}