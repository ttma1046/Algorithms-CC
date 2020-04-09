package hashtable;

import java.util.Arrays;

class Counting_Element_day7 {
    public int countElements(int[] arr) {
        if (arr.length <= 0)
            return 0;
        Arrays.sort(arr);

        int result = 0;
        int prev = -1;
        int prev_count = 0;
        for (int a : arr) {
            if (a == prev) {
                prev_count++;
            } else {
                if (a == prev + 1) {
                    result += prev_count;
                }
                prev = a;
                prev_count = 1;
            }
        }
        return result;
    }

    public int countElements(int[] arr) {
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i : arr) {
            if (!count.containsKey(i))
                count.put(i, 1);
            else
                count.put(i, count.get(i) + 1);
        }

        int occurence = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (count.containsKey(entry.getKey() + 1))
                occurence += entry.getValue();
        }
        return occurence;
    }

    public int countElements(int[] arr) {
        Arrays.sort(arr);
        int count = 0;
        int runLength = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] != arr[i]) {
                if (arr[i - 1] + 1 == arr[i]) {
                    count += runLength;
                }
                runLength = 0;
            }
            runLength++;
        }
        return count;
    }
}