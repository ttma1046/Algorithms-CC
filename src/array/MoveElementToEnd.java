package array;

import java.util.List;

public class MoveElementToEnd {
    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        if (array == null) {
            return null;
        }

        int i = 0;
        int j = array.size() - 1;

        while(i < j) {
            while(i < j && array.get(j) == toMove) {
                j--;
            }

            if (array.get(i) == toMove) {
                swap(array, i, j);
            }

            i++;
        }

        return null;
    }

    private static void swap(List<Integer> array, int positionOne, int positionTwo) {
        int temp = array.get(positionOne);
        array.set(positionOne, array.get(positionTwo));
        array.set(positionTwo, temp);
    }
}
