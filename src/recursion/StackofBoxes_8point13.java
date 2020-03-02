package recursion;

import java.util.ArrayList;
import java.util.Collections;

class StackofBoxes_8point13 {
    int createStack(ArrayList<Box> boxes) {
        /* Sort in decending order by height. */
        Collections.sort(boxes, new BoxComparator());
        int maxHeight = 0;

        for (int i = 0; i < boxes.size(); i++) {
            int height = createStack(boxes, i);
            maxHeight = Math.max(maxHeight, height);
        }

        return maxHeight;
    }

    int createStack(ArrayList<Box> boxes, int bottomIndex) {
        Box bottom = boxes.get(bottomIndex);
        int maxHeight = 0;
        for (int i = bottomIndex + 1; i < boxes.size(); i++) {
            if (boxes.get(i).canBeAbove(bottom)) {
                int height = createStack(boxes, i);
                maxHeight = Math.max(height, maxHeight);
            }
        }

        maxHeight += bottom.height;
        return maxHeight;
    }

    int createStackII(ArrayList<Box> boxes) {
        /* Sort in decending order by height. */
        Collections.sort(boxes, new BoxComparator());
        int maxHeight = 0;
        int[] stackMap = new int[boxes.size()];
        for (int i = 0; i < boxes.size(); i++) {
            int height = createStackII(boxes, i, stackMap);
            maxHeight = Math.max(maxHeight, height);
        }

        return maxHeight;
    }

    int createStackII(ArrayList<Box> boxes, int bottomIndex, int[] stackMap) {
        if (bottomIndex < boxes.size() && stackMap[bottomIndex] > 0) {
            return stackMap[bottomIndex];
        }

        Box bottom = boxes.get(bottomIndex);
        int maxHeight = 0;
        for (int i = bottomIndex + 1; i < boxes.size(); i++) {
            if (boxes.get(i).canBeAbove(bottom)) {
                int height = createStack(boxes, i);
                maxHeight = Math.max(height, maxHeight);
            }
        }

        maxHeight += bottom.height;

        stackMap[bottomIndex] = maxHeight;

        return maxHeight;
    }

    public static void main(String[] args) {
        Box[] boxList = { new Box(6, 4, 4), new Box(8, 6, 2), new Box(5, 3, 3), new Box(7, 8, 3), new Box(4, 2, 2), new Box(9, 7, 3)};
        ArrayList<Box> boxes = new ArrayList<Box>();
        for (Box b : boxList) {
            boxes.add(b);
        }
        
        int height = new StackofBoxes_8point13().createStackII(boxes);
        System.out.println(height);
    }
}