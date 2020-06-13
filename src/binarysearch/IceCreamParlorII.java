package binarysearch;
import java.util.Arrays;

public class IceCreamParlorII {
    private int binarySearch(int [] sortedMenu, int index, int target) {
        int end = sortedMenu.length - 1;
        int start = index;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (sortedMenu[mid] == target) {
                return mid;
            } else if (sortedMenu[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    private int indexOf(int[] cost, int value, int excludeThis) {
        for (int i = 0; i < cost.length; i++) {
            if (cost[i] == value && i != excludeThis) {
                return i;
            }
        }
        return -1;
    }

    private int[] myGetIndexsFromValues(int[] cost, int first, int last) {
        int index1 = indexOf(cost, first, -1);
        int index2 = indexOf(cost, last, index1);

        return index1 < index2 ? new int[] {index1 + 1, index2 + 1} : new int[] {index2 + 1, index1 + 1};
    }

    public int[] whatFlavors(int[] cost, int money) {
        if (cost == null || cost.length <= 0 || money <= 0) {
            return null;
        }

        int[] sortedMenu = cost.clone();

        Arrays.sort(sortedMenu);
        int i = -1;
        int j = -1;
        for(i = 0; i < sortedMenu.length; i++) {
            if (sortedMenu[i] >= money) return null;

            j = binarySearch(sortedMenu, i + 1, money - sortedMenu[i]);

            if (j > 0) {
                return myGetIndexsFromValues(cost, sortedMenu[i], money - sortedMenu[i]);
            }            
        }

        return null;
    }

    public static void main(String[] args) {
        int[] result = new IceCreamParlorII().whatFlavors(new int[] {5,2,3,1,4}, 4);

        for(int value: result) {
            System.out.println(value);
        }

        result = new IceCreamParlorII().whatFlavors(new int[] {2,2,1,4,5}, 4);

        for(int value: result) {
            System.out.println(value);
        }

        result = new IceCreamParlorII().whatFlavors(new int[] {1, 4, 5, 3, 2}, 4);

        for(int value: result) {
            System.out.println(value);
        }

        result = new IceCreamParlorII().whatFlavors(new int[] {2, 2, 4, 3}, 4);

        for(int value: result) {
            System.out.println(value);
        }
    }
}